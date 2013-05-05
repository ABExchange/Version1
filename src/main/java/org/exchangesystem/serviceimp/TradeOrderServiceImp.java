package org.exchangesystem.serviceimp;

import java.util.List;

import org.apache.log4j.Logger;
import org.exchangesystem.dao.TradeOrderDao;
import org.exchangesystem.model.BTCRate;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.OrderStatus;
import org.exchangesystem.model.OrderTransaction;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;
import org.exchangesystem.service.BTCRateService;
import org.exchangesystem.service.OrderTransactionService;
import org.exchangesystem.service.SymbolService;
import org.exchangesystem.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TradeOrderServiceImp implements TradeOrderService {

	protected static Logger logger = Logger
			.getLogger(TradeOrderServiceImp.class);

	// 0.5 / 100
	private static Double FEEMULTIPLIER = 0.005;
	private Boolean processRecords = true;

	@Autowired
	TradeOrderDao tradeOrderDao;

	@Autowired
	OrderTransactionService orderTransactionService;
	
	@Autowired
	SymbolService symbolService;

	@Autowired
	BTCRateService btcRateService;

	public void add(TradeOrder tradeOrder) {
		tradeOrder.setUnfulfilledquantity(tradeOrder.getQuantity());
		tradeOrderDao.save(tradeOrder);
		// executePendingOrders();
		//executePendingOrderVersion2();
		
		//This one takes both the Open and Partial
		/***
		 *I will need to get this refractored - my brain is tired for now
		 *Trust Me! 
		 ***/
		executePendingOrderVersion3();
	}

	public List<TradeOrder> findAll() {
		return tradeOrderDao.findAll();
	}

	public TradeOrder findById(Long id) {
		return tradeOrderDao.findOne(id);
	}

	public void remove(TradeOrder tradeOrder) {
		tradeOrderDao.delete(tradeOrder);
	}

	public void update(TradeOrder tradeOrder) {
		tradeOrderDao.update(tradeOrder);
	}

	public Long count() {
		return tradeOrderDao.count();
	}

	public List<TradeOrder> findOpenBuyOrders(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus) {
		return tradeOrderDao.findOpenBuyOrders(symbol, orderType, orderStatus);
	}

	public List<TradeOrder> findOpenSellOrders(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus, Double price,
			Long quantity) {
		return tradeOrderDao.findOpenSellOrders(symbol, orderType, orderStatus,
				price, quantity);
	}

	public void executePendingOrders() {
		/****
		 * If the order we have is a Buy Order, then 1) Get all the Buy Orders
		 * 2) For each order a) Get all the Orders that have prices lower than
		 * this buy order, are of this symbol, are sell orders (ordertype) and
		 * are open (status) b) For each sell order check if the quantity is
		 * equal or more than that of the buy order then execute the order 1)
		 * Get all the SELL open orders with prices equal to this orders price
		 **/
		List<TradeOrder> listOpenBuyOrders = tradeOrderDao.findOpenBuyOrders(
				OrderType.BUY, OrderStatus.OPEN);
		logger.info(" We got ... " + listOpenBuyOrders.size() + " Orders ");

		for (TradeOrder tradeOrder : listOpenBuyOrders) {
			// Process an Order
			processBuyOrder(tradeOrder);
		}

	}

	public List<TradeOrder> findOpenBuyOrders(OrderType orderType,
			OrderStatus orderStatus) {
		return tradeOrderDao.findOpenBuyOrders(orderType, orderStatus);
	}

	/****
	 * This means, get all the sell orders with pricess less than this orders
	 * price, and quantity is the same
	 ****/
	@Transactional
	private void processBuyOrder(TradeOrder tradeOrder) {

		// Get current btc rate

		BTCRate currentBTCRate = null;
		List<BTCRate> listBtcRate = btcRateService.findAll(tradeOrder
				.getSymbol());
		if (!listBtcRate.equals(null) && (listBtcRate.size() > 0)) {
			currentBTCRate = listBtcRate.get(0);
		}

		// Get the open sell orders in a descending order
		List<TradeOrder> listOpenSellOrders = null;

		OrderTransaction orderTransaction = null;
		TradeOrder sellOrder = null;

		listOpenSellOrders = tradeOrderDao.findOpenSellOrders(
				tradeOrder.getSymbol(), OrderType.SELL, OrderStatus.OPEN,
				tradeOrder.getPrice(), tradeOrder.getQuantity());
		
		if (!listOpenSellOrders.equals(null) && listOpenSellOrders.size() > 0) {
			sellOrder = listOpenSellOrders.get(0);
			logger.info("XXXXXXXX Executing an Order XXXXXXXX ");
			// Execute the Orders
			// Save a record of tradeorder in the Orders Transaction
			orderTransaction = new OrderTransaction();

			orderTransaction.setTradeOrder(tradeOrder);
			orderTransaction.setQuantity(sellOrder.getQuantity());
			orderTransaction.setPrice(sellOrder.getPrice());
			orderTransaction.setTotal(sellOrder.getPrice()
					* sellOrder.getQuantity());
			orderTransaction.setFee(FEEMULTIPLIER * sellOrder.getPrice()
					* sellOrder.getQuantity());
			orderTransaction.setSymbol(sellOrder.getSymbol());
			orderTransaction.setCorrespondingOrder(sellOrder);
			orderTransaction.setOrderType(tradeOrder.getOrderType());
			orderTransaction.setBtcRate(currentBTCRate);

			orderTransactionService.add(orderTransaction);
			// Set the tradeOrder to closed

			tradeOrder.setOrderStatus(OrderStatus.CLOSED);
			// tradeOrderDao.save(tradeOrder);

			// Save the sell Order in the Order transaction
			orderTransaction.setTradeOrder(sellOrder);
			orderTransaction.setCorrespondingOrder(tradeOrder);
			orderTransaction.setOrderType(sellOrder.getOrderType());
			orderTransactionService.add(orderTransaction);

			// Set the Sell Order as closed
			sellOrder.setOrderStatus(OrderStatus.CLOSED);

			tradeOrderDao.update(tradeOrder);
			tradeOrderDao.update(sellOrder);
		}
	
	}

	public List<TradeOrder> findAll(OrderStatus orderStatus) {
		return tradeOrderDao.findAll(orderStatus);
	}

	public List<TradeOrder> findOpenSellOrdersNewestFirst(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus) {
		return tradeOrderDao.findOpenSellOrdersNewestFirst(symbol, orderType,
				orderStatus);
	}

	public List<TradeOrder> findOpenBuyOrdersNewestFirst(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus) {
		return tradeOrderDao.findOpenBuyOrdersNewestFirst(symbol, orderType,
				orderStatus);
	}

	/***
	 * Processes the orders
	 * */
	public void executePendingOrderVersion2() {

		/****
		 * Get all the open buy orders ordered by their prices, The first Order
		 * should be the one with highest price - willing to pay more
		 * (descending prices)
		 * **/
		logger.info("Got into executePendingOrderVersion2 !!!");

		List<TradeOrder> listPendingOrder = null;
		processRecords = true;
		List<Symbol> listSymbol = symbolService.findAll();
		
		for (Symbol symbol : listSymbol) {
			processRecords = true;
			while (processRecords){ //Do this until the day when processed record gets to false
				//Then stop
				logger.info("Get into the while loop to try and process the orders! processRecords is true");
				
			listPendingOrder = tradeOrderDao.findOpenBuyOrders(symbol,
					OrderType.BUY, OrderStatus.OPEN);

			if (!listPendingOrder.equals(null) && (listPendingOrder.size() > 0)) {
				// We have a buy order that is open
				// Let us process it - let us look for a matching order
				TradeOrder processBuyOrder = listPendingOrder.get(0);
				matchBuyOrder(processBuyOrder);
			} else{
				processRecords = false;
				logger.info("OOOOOOOOOOOO No OPEN BUY ORDERS means No Processing !");
			}
			
			}			
		}
		


	}

	/****
	 * Given a tradeOrder, match it to a sell order
	 * */
	private void matchBuyOrder(TradeOrder tradeOrder) {
		BTCRate currentBTCRate = null;
		List<BTCRate> listBtcRate = btcRateService.findAll(tradeOrder
				.getSymbol());
		if (!listBtcRate.equals(null) && (listBtcRate.size() > 0)) {
			currentBTCRate = listBtcRate.get(0);
		}

		// Get the open sell orders in a descending order
		List<TradeOrder> listOpenSellOrders = null;

		OrderTransaction orderTransaction = null;
		TradeOrder sellOrder = null;
		listOpenSellOrders = tradeOrderDao.findOpenSellOrders(
				tradeOrder.getSymbol(), OrderType.SELL, OrderStatus.OPEN,
				tradeOrder.getPrice());
		logger.info(" DDDDDDDDD ORDER Symbol "+tradeOrder.getSymbol()+" Type "+OrderType.SELL+" status "+OrderStatus.OPEN+" Price "+tradeOrder.getPrice());
		if (!listOpenSellOrders.equals(null) && listOpenSellOrders.size() > 0) {
			sellOrder = listOpenSellOrders.get(0);
			logger.info("XXXXXXXX Executing an Order XXXXXXXX ");

			/***
			 * Quantities Equal Create a Buy Order Transaction Create a Sell
			 * Order Transaction Close a Buy Order Close a Sell Order
			 * **/
			logger.info("RRRRRRRRRRRRRRR Buy Order price  "+tradeOrder.getPrice()+" By Order Quantity "+tradeOrder.getUnfulfilledquantity()+" Sell Order Price "+sellOrder.getPrice()+" Sell Order Quantity "+sellOrder.getUnfulfilledquantity());
			if (tradeOrder.getUnfulfilledquantity().equals(
					sellOrder.getUnfulfilledquantity())) {
				// Task happens here
				orderTransaction = new OrderTransaction();
				orderTransaction.setTradeOrder(tradeOrder);
				orderTransaction.setQuantity(sellOrder.getQuantity());
				orderTransaction.setPrice(sellOrder.getPrice());
				
				
				orderTransaction.setTotal(sellOrder.getPrice()
						* sellOrder.getQuantity());
				orderTransaction.setFee(FEEMULTIPLIER * sellOrder.getPrice()
						* sellOrder.getQuantity());
				orderTransaction.setSymbol(sellOrder.getSymbol());
				orderTransaction.setCorrespondingOrder(sellOrder);
				orderTransaction.setOrderType(tradeOrder.getOrderType());
				orderTransaction.setBtcRate(currentBTCRate);

				orderTransactionService.add(orderTransaction);
				// Set the tradeOrder to closed

				tradeOrder.setOrderStatus(OrderStatus.CLOSED);
				// tradeOrderDao.save(tradeOrder);

				// Save the sell Order in the Order transaction
				orderTransaction.setTradeOrder(sellOrder);
				orderTransaction.setCorrespondingOrder(tradeOrder);
				orderTransaction.setOrderType(sellOrder.getOrderType());
				orderTransactionService.add(orderTransaction);

				// Set the Sell Order as closed
				sellOrder.setOrderStatus(OrderStatus.CLOSED);

				tradeOrderDao.update(tradeOrder);
				tradeOrderDao.update(sellOrder);

			} else
			/***
			 * Buy Order Quantity is less than Sell Order Quantity
			 * 
			 * Create Buy Order Transaction Close Buy Order Create a Sell Order
			 * Transaction with small quantity
			 * 
			 * Update Sell Order with the new unfulfilled quantity - sell
			 * unfulfilled - buy unfulfilled
			 * ***/
			if (tradeOrder.getUnfulfilledquantity().compareTo(
					sellOrder.getUnfulfilledquantity()) < 0) {
				// Task happens here
				orderTransaction = new OrderTransaction();
				orderTransaction.setTradeOrder(tradeOrder);
				orderTransaction.setQuantity(tradeOrder.getUnfulfilledquantity());
				orderTransaction.setPrice(sellOrder.getPrice());
				orderTransaction.setTotal(sellOrder.getPrice() * tradeOrder.getUnfulfilledquantity());
				orderTransaction.setFee(FEEMULTIPLIER * sellOrder.getPrice() * tradeOrder.getUnfulfilledquantity() ); 
				orderTransaction.setSymbol(sellOrder.getSymbol());
				orderTransaction.setCorrespondingOrder(sellOrder);
				orderTransaction.setOrderType(tradeOrder.getOrderType());
				orderTransaction.setBtcRate(currentBTCRate);
				
				orderTransactionService.add(orderTransaction);
				//Set the tradeOrder to closed
				
				tradeOrder.setOrderStatus(OrderStatus.CLOSED);
				//tradeOrderDao.save(tradeOrder);
				
				//Save the sell Order in the Order transaction
				orderTransaction.setTradeOrder(sellOrder);
				orderTransaction.setCorrespondingOrder(tradeOrder);
				orderTransaction.setOrderType(sellOrder.getOrderType());
				orderTransactionService.add(orderTransaction);
				
				//Set the Sell Order as closed
				//sellOrder.setOrderStatus(OrderStatus.CLOSED);
				
				
				tradeOrderDao.update(tradeOrder);
				
				sellOrder.setUnfulfilledquantity(sellOrder.getUnfulfilledquantity().longValue() - tradeOrder.getUnfulfilledquantity().longValue());
				sellOrder.setOrderStatus(OrderStatus.PARTIAL);
				tradeOrderDao.update(sellOrder);


			}
			/****
			 * Buy Order is quantity is greater than sell order quantity Create
			 * Buy Order Transaction with quantity equal to sell order quantity
			 * update buy order partial quanity - buy order partial - sell order
			 * partial
			 * 
			 * Create a sell order transaction close sell order
			 * 
			 * 
			 ***/
			else if (tradeOrder.getUnfulfilledquantity().compareTo(
					sellOrder.getUnfulfilledquantity()) > 0) {
				
				orderTransaction = new OrderTransaction();
				orderTransaction.setTradeOrder(tradeOrder);
				orderTransaction.setQuantity(sellOrder.getUnfulfilledquantity());
				orderTransaction.setPrice(sellOrder.getPrice());
				orderTransaction.setTotal(sellOrder.getPrice() * sellOrder.getUnfulfilledquantity());
				orderTransaction.setFee(FEEMULTIPLIER * sellOrder.getPrice() * sellOrder.getUnfulfilledquantity() ); 
				orderTransaction.setSymbol(sellOrder.getSymbol());
				orderTransaction.setCorrespondingOrder(sellOrder);
				orderTransaction.setOrderType(tradeOrder.getOrderType());
				orderTransaction.setBtcRate(currentBTCRate);
				
				orderTransactionService.add(orderTransaction);
				//Set the tradeOrder to closed
				
				tradeOrder.setOrderStatus(OrderStatus.PARTIAL);
				//tradeOrderDao.save(tradeOrder);
				
				//Save the sell Order in the Order transaction
				orderTransaction.setTradeOrder(sellOrder);
				orderTransaction.setCorrespondingOrder(tradeOrder);
				orderTransaction.setOrderType(sellOrder.getOrderType());
				orderTransactionService.add(orderTransaction);
				
				//Set the Sell Order as closed
				//sellOrder.setOrderStatus(OrderStatus.CLOSED);
				
				tradeOrder.setUnfulfilledquantity(tradeOrder.getUnfulfilledquantity().longValue() - sellOrder.getUnfulfilledquantity().longValue());
				tradeOrderDao.update(tradeOrder);
				
				//sellOrder.setUnfulfilledquantity(sellOrder.getUnfulfilledquantity().longValue() - tradeOrder.getUnfulfilledquantity().longValue());
				sellOrder.setOrderStatus(OrderStatus.CLOSED);
				tradeOrderDao.update(sellOrder);


			}
			// Execute the Orders
			// Save a record of tradeorder in the Orders Transaction
			// orderTransaction = new OrderTransaction();
			// orderTransaction.setTradeOrder(tradeOrder);
		}
			else {
			//There was not matching order in the sell orders
			//This means, no need doing this again
			processRecords = false;
			logger.info("DDDDDDDDDDDD Didnt find any matching SELL ORDER, therefore will not continue with any processing here - stopped !");

		}

	}
	
	/***
	 * Processes the orders -  both Open and Partial Orders are included here
	 * */
	public void executePendingOrderVersion3() {

		/****
		 * Get all the open buy orders ordered by their prices, The first Order
		 * should be the one with highest price - willing to pay more
		 * (descending prices)
		 * **/
		logger.info("Got into executePendingOrderVersion2 !!!");

		List<TradeOrder> listPendingOrder = null;
		processRecords = true;
		List<Symbol> listSymbol = symbolService.findAll();
		
		for (Symbol symbol : listSymbol) {
			processRecords = true;
			while (processRecords){ //Do this until the day when processed record gets to false
				//Then stop
				logger.info("Get into the while loop to try and process the orders! processRecords is true");
				
//			listPendingOrder = tradeOrderDao.findOpenBuyOrders(symbol,
//					OrderType.BUY, OrderStatus.OPEN);
			listPendingOrder = tradeOrderDao.findOpenAndPartialBuyOrders(symbol,
					OrderType.BUY, OrderStatus.OPEN, OrderStatus.PARTIAL);

			
			if (!listPendingOrder.equals(null) && (listPendingOrder.size() > 0)) {
				// We have a buy order that is open
				// Let us process it - let us look for a matching order
				TradeOrder processBuyOrder = listPendingOrder.get(0);
				matchBuyOpenPartialOrder(processBuyOrder);
			} else{
				processRecords = false;
				logger.info("OOOOOOOOOOOO No OPEN BUY ORDERS means No Processing !");
			}
			
			}			
		}
		


	}
	
	/****
	 * Proper Matching that includes both Open and Partial Sell Orders
	 * */
	private void matchBuyOpenPartialOrder(TradeOrder tradeOrder){
		BTCRate currentBTCRate = null;
		List<BTCRate> listBtcRate = btcRateService.findAll(tradeOrder
				.getSymbol());
		if (!listBtcRate.equals(null) && (listBtcRate.size() > 0)) {
			currentBTCRate = listBtcRate.get(0);
		}

		// Get the open sell orders in a descending order
		List<TradeOrder> listOpenSellOrders = null;

		OrderTransaction orderTransaction = null;
		OrderTransaction sellOrderTransaction = null;
		TradeOrder sellOrder = null;
//		listOpenSellOrders = tradeOrderDao.findOpenSellOrders(
//				tradeOrder.getSymbol(), OrderType.SELL, OrderStatus.OPEN,
//				tradeOrder.getPrice());
		
		//Include Partials in the list
		listOpenSellOrders = tradeOrderDao.findOpenAndPartialSellOrders(
				tradeOrder.getSymbol(), OrderType.SELL, OrderStatus.OPEN, OrderStatus.PARTIAL,
				tradeOrder.getPrice());

		logger.info(" DDDDDDDDD ORDER Symbol "+tradeOrder.getSymbol()+" Type "+OrderType.SELL+" status "+OrderStatus.OPEN+" Price "+tradeOrder.getPrice());
		if (!listOpenSellOrders.equals(null) && listOpenSellOrders.size() > 0) {
			sellOrder = listOpenSellOrders.get(0);
			logger.info("XXXXXXXX Executing an Order XXXXXXXX ");

			/***
			 * Quantities Equal Create a Buy Order Transaction Create a Sell
			 * Order Transaction Close a Buy Order Close a Sell Order
			 * **/
			logger.info("RRRRRRRRRRRRRRR Buy Order price  "+tradeOrder.getPrice()+" By Order Quantity "+tradeOrder.getUnfulfilledquantity()+" Sell Order Price "+sellOrder.getPrice()+" Sell Order Quantity "+sellOrder.getUnfulfilledquantity());
			if (tradeOrder.getUnfulfilledquantity().equals(
					sellOrder.getUnfulfilledquantity())) {
				
				//This is independent of whether it is a sell or a buy 
				// Task happens here
				orderTransaction = new OrderTransaction();
				orderTransaction.setTradeOrder(tradeOrder);
				orderTransaction.setQuantity(sellOrder.getUnfulfilledquantity());
				orderTransaction.setPrice(sellOrder.getPrice());
				
				Double transactionFee = (FEEMULTIPLIER * sellOrder.getPrice() * sellOrder.getUnfulfilledquantity());
				
				orderTransaction.setTotal(((sellOrder.getPrice()
						* sellOrder.getUnfulfilledquantity()) + transactionFee));
				
				orderTransaction.setFee(FEEMULTIPLIER * sellOrder.getPrice()
						* sellOrder.getUnfulfilledquantity());
				orderTransaction.setSymbol(sellOrder.getSymbol());
				orderTransaction.setCorrespondingOrder(sellOrder);
				//orderTransaction.getSourceOrderList().add(sellOrder);
				orderTransaction.setOrderType(tradeOrder.getOrderType());
				orderTransaction.setBtcRate(currentBTCRate);
				sellOrderTransaction = orderTransaction;
				
				//if buy order is open
				if (tradeOrder.getOrderStatus().equals(OrderStatus.OPEN)){

					orderTransactionService.add(orderTransaction);
					// Set the tradeOrder to closed

					tradeOrder.setOrderStatus(OrderStatus.CLOSED);
					tradeOrderDao.update(tradeOrder);
										
				} else if (tradeOrder.getOrderStatus().equals(OrderStatus.PARTIAL)){
					//we have a partial buy order
					/***
					 * Get the buy order transaction and update it
					 * */
					updatePartialBuyOrderBuySellEqual(tradeOrder, sellOrder);
					
				}
								// tradeOrderDao.save(tradeOrder);

				if (sellOrder.getOrderStatus().equals(OrderStatus.OPEN)){
					//The Sell Order is Open - normal operation
					sellOrderTransaction.setTradeOrder(sellOrder);
					sellOrderTransaction.setCorrespondingOrder(tradeOrder);
					sellOrderTransaction.setOrderType(sellOrder.getOrderType());
					orderTransactionService.add(sellOrderTransaction);
					sellOrder.setOrderStatus(OrderStatus.CLOSED);
					tradeOrderDao.update(sellOrder);
				} else if (sellOrder.getOrderStatus().equals(OrderStatus.PARTIAL)){					
					/***
					 * Get the buy order transaction and update it
					 * */
					updatePartialSellOrderBuySellEqual(tradeOrder, sellOrder);
					
				}


				

			} else
			/***
			 * Buy Order Quantity is less than Sell Order Quantity
			 * 
			 * Create Buy Order Transaction Close Buy Order Create a Sell Order
			 * Transaction with small quantity
			 * 
			 * Update Sell Order with the new unfulfilled quantity - sell
			 * unfulfilled - buy unfulfilled
			 * ***/
			if (tradeOrder.getUnfulfilledquantity().compareTo(
					sellOrder.getUnfulfilledquantity()) < 0) {
				
				//This is independent of whether it is a buy or a sell - this block of code before the if
				// Task happens here
				orderTransaction = new OrderTransaction();
				orderTransaction.setTradeOrder(tradeOrder);
				orderTransaction.setQuantity(tradeOrder.getUnfulfilledquantity());
				orderTransaction.setPrice(sellOrder.getPrice());
				
				Double transactionFee = (FEEMULTIPLIER * sellOrder.getPrice() * tradeOrder.getUnfulfilledquantity());
				
				orderTransaction.setTotal(((sellOrder.getPrice()
						* tradeOrder.getUnfulfilledquantity()) + transactionFee));
				
//				orderTransaction.setTotal(sellOrder.getPrice() * tradeOrder.getUnfulfilledquantity());
//				orderTransaction.setFee(FEEMULTIPLIER * sellOrder.getPrice() * tradeOrder.getUnfulfilledquantity() ); 
				orderTransaction.setFee(transactionFee);
				orderTransaction.setSymbol(sellOrder.getSymbol());
				orderTransaction.setCorrespondingOrder(sellOrder);
				orderTransaction.setOrderType(tradeOrder.getOrderType());
				orderTransaction.setBtcRate(currentBTCRate);
				sellOrderTransaction = orderTransaction;
				
				/**
				 * Buy Order is Open - Normal Processing
				 * **/
				if (tradeOrder.getOrderStatus().equals(OrderStatus.OPEN)){
					

					orderTransactionService.add(orderTransaction);
					//Set the tradeOrder to closed
					
					tradeOrder.setOrderStatus(OrderStatus.CLOSED);
					//tradeOrderDao.save(tradeOrder);

					tradeOrderDao.update(tradeOrder);

				} 
				//Partial Order - add it to an existing order transaction
				else if (tradeOrder.getOrderStatus().equals(OrderStatus.PARTIAL)){
					//Adding the trade order to a list and finally closing it up
					/*****************/
					updatePartialBuyOrderBuyLessThanSell(tradeOrder, sellOrder);
					
				}
				
				
				/**
				 * Open Sell Order - do the processing normally
				 * **/
				if (sellOrder.getOrderStatus().equals(OrderStatus.OPEN)){
					
					
					//Save the sell Order in the Order transaction
					sellOrderTransaction.setTradeOrder(sellOrder);
					sellOrderTransaction.setCorrespondingOrder(tradeOrder);
					sellOrderTransaction.setOrderType(sellOrder.getOrderType());
					
					tradeOrder.setPartialquantity(tradeOrder.getUnfulfilledquantity());
	//				sellOrderTransaction.getSourceOrderList().add(tradeOrder);
					
					orderTransactionService.add(sellOrderTransaction);
					
					//Set the Sell Order as closed
					//sellOrder.setOrderStatus(OrderStatus.CLOSED);
					
					
					
					sellOrder.setUnfulfilledquantity(sellOrder.getUnfulfilledquantity().longValue() - tradeOrder.getUnfulfilledquantity().longValue());
					sellOrder.setOrderStatus(OrderStatus.PARTIAL);
					tradeOrderDao.update(sellOrder);

					
				}
				/**
				 * PARTAIL SELL ORDER -  ADD IT TO A SELL ORDER TRANSACTION
				 * **/
				else if (sellOrder.getOrderStatus().equals(OrderStatus.PARTIAL)){
					updatePartialSellOrderBuyLessThanSell(tradeOrder, sellOrder);
				}


			}
			/****
			 * Buy Order is quantity is greater than sell order quantity Create
			 * Buy Order Transaction with quantity equal to sell order quantity
			 * update buy order partial quanity - buy order partial - sell order
			 * partial
			 * 
			 * Create a sell order transaction close sell order
			 * 
			 * 
			 ***/
			else if (tradeOrder.getUnfulfilledquantity().compareTo(
					sellOrder.getUnfulfilledquantity()) > 0) {
				
				/****
				 * This block is independent of whether it is a sell or a buy
				 *
				 * **/
				
				orderTransaction = new OrderTransaction();
				orderTransaction.setTradeOrder(tradeOrder);
				orderTransaction.setQuantity(sellOrder.getUnfulfilledquantity());
				orderTransaction.setPrice(sellOrder.getPrice());
				
				
				Double transactionFee = (FEEMULTIPLIER * sellOrder.getPrice() * sellOrder.getUnfulfilledquantity());
				
				orderTransaction.setFee(transactionFee);
				
				orderTransaction.setTotal(((sellOrder.getPrice()
						* sellOrder.getUnfulfilledquantity()) + transactionFee));

				
//				orderTransaction.setTotal(sellOrder.getPrice() * sellOrder.getUnfulfilledquantity());
//				orderTransaction.setFee(FEEMULTIPLIER * sellOrder.getPrice() * sellOrder.getUnfulfilledquantity() ); 
				orderTransaction.setSymbol(sellOrder.getSymbol());
				orderTransaction.setCorrespondingOrder(sellOrder);
				orderTransaction.setOrderType(tradeOrder.getOrderType());
				orderTransaction.setBtcRate(currentBTCRate);
				sellOrderTransaction = orderTransaction;

				
				/***
				 *This is an Open Order
				 *Normal processing 
				 ***/
				if (tradeOrder.getOrderStatus().equals(OrderStatus.OPEN)){
					
					sellOrder.setPartialquantity(sellOrder.getUnfulfilledquantity());
//					orderTransaction.getSourceOrderList().add(sellOrder);
					
					orderTransactionService.add(orderTransaction);
					//Set the tradeOrder to closed
					
					tradeOrder.setOrderStatus(OrderStatus.PARTIAL);
					//tradeOrderDao.save(tradeOrder);
					
					tradeOrder.setUnfulfilledquantity(tradeOrder.getUnfulfilledquantity().longValue() - sellOrder.getUnfulfilledquantity().longValue());
					tradeOrderDao.update(tradeOrder);
					
				} 
				/***
				 *This is a partial Order
				 *Add it to an initial transaction 
				 ***/
				else if (tradeOrder.getOrderStatus().equals(OrderStatus.PARTIAL)){
					updatePartialBuyOrderBuyGreaterThanSell(tradeOrder, sellOrder);
				}
				

				/****
				 * The Sell Order is Open
				 * Normal Processing
				 ***/
				if (sellOrder.getOrderStatus().equals(OrderStatus.OPEN)){
					
					
					sellOrderTransaction.setTradeOrder(sellOrder);
					sellOrderTransaction.setCorrespondingOrder(tradeOrder);
					sellOrderTransaction.setOrderType(sellOrder.getOrderType());
					orderTransactionService.add(sellOrderTransaction);
					
					//Set the Sell Order as closed
					//sellOrder.setOrderStatus(OrderStatus.CLOSED);
					
					
					//sellOrder.setUnfulfilledquantity(sellOrder.getUnfulfilledquantity().longValue() - tradeOrder.getUnfulfilledquantity().longValue());
					sellOrder.setOrderStatus(OrderStatus.CLOSED);
					tradeOrderDao.update(sellOrder);

					
				} else if (sellOrder.getOrderStatus().equals(OrderStatus.PARTIAL)){
					updatePartialSellOrderBuyGreaterThanSell(tradeOrder, sellOrder);
					
				}
				//Save the sell Order in the Order transaction


			}
			// Execute the Orders
			// Save a record of tradeorder in the Orders Transaction
			// orderTransaction = new OrderTransaction();
			// orderTransaction.setTradeOrder(tradeOrder);
		}
			else {
			//There was not matching order in the sell orders
			//This means, no need doing this again
			processRecords = false;
			logger.info("DDDDDDDDDDDD Didnt find any matching SELL ORDER, therefore will not continue with any processing here - stopped !");

		}
		
	}

	public List<TradeOrder> findOpenSellOrders(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus, Double price) {
		return tradeOrderDao.findOpenSellOrders(symbol, orderType, orderStatus,
				price);
	}

	/***
	 * All new Methods here
	 * */
	public List<TradeOrder> findOpenPartialBuyOrdersNewestFirst(Symbol symbol,
			OrderType orderType, OrderStatus orderStatusOpen,
			OrderStatus orderStatusPartial) {
		return tradeOrderDao.findOpenPartialBuyOrdersNewestFirst(symbol, orderType, orderStatusOpen, orderStatusPartial);
	}

	public List<TradeOrder> findOpenPartialSellOrdersNewestFirst(Symbol symbol,
			OrderType orderType, OrderStatus orderStatusOpen,
			OrderStatus orderStatusPartial) {
		return tradeOrderDao.findOpenPartialSellOrdersNewestFirst(symbol, orderType, orderStatusOpen, orderStatusPartial);
	}

	public List<TradeOrder> findOpenAndPartialBuyOrders(Symbol symbol,
			OrderType orderType, OrderStatus orderStatusOpen,
			OrderStatus orderStatusPartial) {
		return tradeOrderDao.findOpenAndPartialBuyOrders(symbol, orderType, orderStatusOpen, orderStatusPartial);
	}

	public List<TradeOrder> findOpenAndPartialSellOrders(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus,
			OrderStatus orderStatusPartial, Double price) {
		return tradeOrderDao.findOpenAndPartialSellOrders(symbol, orderType, orderStatus, orderStatusPartial, price);
	}

	public List<TradeOrder> findAllUnclosed(OrderStatus orderStatus) {
		return tradeOrderDao.findAllUnclosed(OrderStatus.CLOSED);
	}

	
	/*****
	 *Update the buy order transaction with the buy order 
	 * 
	 ****/
	private void updatePartialBuyOrderBuySellEqual(TradeOrder tradeOrder, TradeOrder sellOrder){
		//Get the Transaction record for this order
		OrderTransaction orderTransaction = null;
		List<OrderTransaction> listOrderTransaction = orderTransactionService.findAll(tradeOrder, tradeOrder.getSymbol());
		
		if (!listOrderTransaction.equals(null) && listOrderTransaction.size() > 0){
			logger.info(" Got an order transaction record "+listOrderTransaction.size()+" Reccords -- Let me do the processing" );
			//Got an Order Transaction in
			//Do the process
			orderTransaction = listOrderTransaction.get(0);
			
			Double  transactionTotal = ((sellOrder.getPrice() * sellOrder.getUnfulfilledquantity()) + (sellOrder.getPrice() * sellOrder.getUnfulfilledquantity() * FEEMULTIPLIER));
			Double transactionFee = (sellOrder.getPrice() * sellOrder.getUnfulfilledquantity() * FEEMULTIPLIER);
			//Increase the Transaction Order total
			orderTransaction.setTotal(orderTransaction.getTotal().doubleValue() + transactionTotal);
			orderTransaction.setFee(orderTransaction.getFee() + transactionFee);
			
			//Increase the transaction order quantity
			orderTransaction.setQuantity(orderTransaction.getQuantity().longValue() + sellOrder.getUnfulfilledquantity());
			
			//Set the sell order partial to it's quantity
			sellOrder.setPartialquantity(tradeOrder.getUnfulfilledquantity());
			orderTransaction.getSourceOrderList().add(sellOrder);
			
			orderTransactionService.update(orderTransaction);
			
			tradeOrder.setOrderStatus(OrderStatus.CLOSED);
			
			tradeOrderDao.update(tradeOrder);
			
		} else{
			logger.info("No Order transaction record !!!");
		}
		
	}
	
	
	/*****
	 *Update the Sell order transaction with the Sell Order 
	 * 
	 ****/
	private void updatePartialSellOrderBuySellEqual(TradeOrder tradeOrder, TradeOrder sellOrder){
		//Get the Transaction record for this order
		OrderTransaction orderTransaction = null;
		List<OrderTransaction> listOrderTransaction = orderTransactionService.findAll(sellOrder, sellOrder.getSymbol());
		
		if (!listOrderTransaction.equals(null) && listOrderTransaction.size() > 0){
			logger.info(" Got an order transaction record "+listOrderTransaction.size()+" Reccords -- Let me do the processing" );
			//Got an Order Transaction in
			//Do the process
			orderTransaction = listOrderTransaction.get(0);
			
			Double  transactionTotal = ((sellOrder.getPrice() * sellOrder.getUnfulfilledquantity()) + (sellOrder.getPrice() * sellOrder.getUnfulfilledquantity() * FEEMULTIPLIER));
			Double transactionFee = (sellOrder.getPrice() * sellOrder.getUnfulfilledquantity() * FEEMULTIPLIER);
			//Increase the Transaction Order total
			orderTransaction.setTotal(orderTransaction.getTotal().doubleValue() + transactionTotal);
			orderTransaction.setFee(orderTransaction.getFee() + transactionFee);
			
			//Increase the transaction order quantity
			orderTransaction.setQuantity(orderTransaction.getQuantity().longValue() + sellOrder.getUnfulfilledquantity());
			
			//Set the sell order partial to it's quantity
			tradeOrder.setPartialquantity(sellOrder.getUnfulfilledquantity());
			orderTransaction.getSourceOrderList().add(tradeOrder);
			
			orderTransactionService.update(orderTransaction);
			
			sellOrder.setOrderStatus(OrderStatus.CLOSED);
			
			tradeOrderDao.update(sellOrder);
			
		} else{
			logger.info("No Order transaction record !!!");
		}
	}

	/***
	 * Buy Order is partial
	 * Buy Order is greater than sell order
	 * 
	 * Add Buy Order to the buy order transanctions
	 * Close Buy Order
	 * 
	 * **/
	private void updatePartialBuyOrderBuyLessThanSell(TradeOrder tradeOrder, TradeOrder sellOrder){
		//Get the Transaction record for this order
		OrderTransaction orderTransaction = null;
		List<OrderTransaction> listOrderTransaction = orderTransactionService.findAll(tradeOrder, tradeOrder.getSymbol());
		
		if (!listOrderTransaction.equals(null) && listOrderTransaction.size() > 0){
			logger.info(" Got an order transaction record "+listOrderTransaction.size()+" Reccords -- Let me do the processing" );
			//Got an Order Transaction in
			//Do the process
			orderTransaction = listOrderTransaction.get(0);
			
			Double  transactionTotal = ((sellOrder.getPrice() * tradeOrder.getUnfulfilledquantity()) + (sellOrder.getPrice() * tradeOrder.getUnfulfilledquantity() * FEEMULTIPLIER));
			Double transactionFee =  (sellOrder.getPrice() * tradeOrder.getUnfulfilledquantity() * FEEMULTIPLIER);
			//Increase the Transaction Order total
			orderTransaction.setTotal(orderTransaction.getTotal().doubleValue() + transactionTotal);
			orderTransaction.setFee(orderTransaction.getFee()+transactionFee);
			
			//Increase the transaction order quantity
			orderTransaction.setQuantity(orderTransaction.getQuantity().longValue() + tradeOrder.getUnfulfilledquantity());
			
			//Set the sell order partial to it's quantity
			sellOrder.setPartialquantity(tradeOrder.getUnfulfilledquantity());
			orderTransaction.getSourceOrderList().add(sellOrder);
			
			orderTransactionService.update(orderTransaction);
			
			tradeOrder.setOrderStatus(OrderStatus.CLOSED);
			
			tradeOrderDao.update(tradeOrder);
			
		} else{
			logger.info("No Order transaction record !!!");
		}
	
	}
	
	/***
	 * Sell Order is partial
	 * Buy Order is greater than sell order
	 * 
	 * Add Sell Order to the Sell order transanctions
	 * Reduce the Sell Order Partial
	 * 
	 * **/
	private void updatePartialSellOrderBuyLessThanSell(TradeOrder tradeOrder, TradeOrder sellOrder){
		//Get the Transaction record for this order
		OrderTransaction orderTransaction = null;
		List<OrderTransaction> listOrderTransaction = orderTransactionService.findAll(sellOrder, sellOrder.getSymbol());
		
		if (!listOrderTransaction.equals(null) && listOrderTransaction.size() > 0){
			logger.info(" Got an order transaction record "+listOrderTransaction.size()+" Reccords -- Let me do the processing" );
			//Got an Order Transaction in
			//Do the process
			orderTransaction = listOrderTransaction.get(0);
			
			Double transactionalFee = (sellOrder.getPrice() * tradeOrder.getUnfulfilledquantity() * FEEMULTIPLIER);
			Double  transactionTotal = ((sellOrder.getPrice() * tradeOrder.getUnfulfilledquantity()) +transactionalFee );
			
			//Increase the Transaction Order total
			orderTransaction.setTotal(orderTransaction.getTotal().doubleValue() + transactionTotal);
			orderTransaction.setFee(orderTransaction.getFee() + transactionalFee);
			//Increase the transaction order quantity
			orderTransaction.setQuantity(orderTransaction.getQuantity().longValue() + tradeOrder.getUnfulfilledquantity());
			
			//Set the sell order partial to it's quantity
			tradeOrder.setPartialquantity(tradeOrder.getUnfulfilledquantity());
			sellOrder.setUnfulfilledquantity(sellOrder.getUnfulfilledquantity().longValue() - tradeOrder.getUnfulfilledquantity());
			
			//Trade Order is the buy Order
			orderTransaction.getSourceOrderList().add(tradeOrder);
			
			orderTransactionService.update(orderTransaction);
			
			
			tradeOrderDao.update(sellOrder);
			
		} else{
			logger.info("No Order transaction record !!!");
		}
	
	}
	
	/****
	 *The Buy Order is partial
	 *The Buy Order Quantity (unfulfilled) is greater than the sell order
	 *Add the Buy Order to an existing Buy Order Transaction
	 * 
	 ***/
	private void updatePartialBuyOrderBuyGreaterThanSell(TradeOrder tradeOrder,  TradeOrder sellOrder){
		//Get the Transaction record for this order
		OrderTransaction orderTransaction = null;
		List<OrderTransaction> listOrderTransaction = orderTransactionService.findAll(tradeOrder, tradeOrder.getSymbol());
		
		if (!listOrderTransaction.equals(null) && listOrderTransaction.size() > 0){
			logger.info(" Got an order transaction record "+listOrderTransaction.size()+" Reccords -- Let me do the processing" );
			//Got an Order Transaction in
			//Do the process
			orderTransaction = listOrderTransaction.get(0);
			
			Double  transactionTotal = ((sellOrder.getPrice() * sellOrder.getUnfulfilledquantity()) + (sellOrder.getPrice() * sellOrder.getUnfulfilledquantity() * FEEMULTIPLIER));
			Double transactionalFee = (sellOrder.getPrice() * sellOrder.getUnfulfilledquantity() * FEEMULTIPLIER);
			//Increase the Transaction Order total
			orderTransaction.setTotal(orderTransaction.getTotal().doubleValue() + transactionTotal);
			orderTransaction.setFee(orderTransaction.getFee() + transactionalFee);
			
			//Increase the transaction order quantity
			orderTransaction.setQuantity(orderTransaction.getQuantity().longValue() + sellOrder.getUnfulfilledquantity());
			
			//Set the sell order partial to it's quantity
			sellOrder.setPartialquantity(sellOrder.getUnfulfilledquantity());
			orderTransaction.getSourceOrderList().add(sellOrder);
			
			orderTransactionService.update(orderTransaction);
			tradeOrder.setUnfulfilledquantity(tradeOrder.getUnfulfilledquantity() - sellOrder.getUnfulfilledquantity());
			//tradeOrder.setOrderStatus(OrderStatus.CLOSED);
			
			tradeOrderDao.update(tradeOrder);
			
		} else{
			logger.info("No Order transaction record !!!");
		}
		
	}
	

	/*****
	 * 
	 *The Sell Order is Partial
	 *The Buy Order is greater than then sell order
	 *
	 * Add the Sell Order to an existing sell order transaction
	 ****/
	private void updatePartialSellOrderBuyGreaterThanSell(TradeOrder tradeOrder, TradeOrder sellOrder){
		//Get the Transaction record for this order
		OrderTransaction orderTransaction = null;
		List<OrderTransaction> listOrderTransaction = orderTransactionService.findAll(sellOrder, sellOrder.getSymbol());
		
		if (!listOrderTransaction.equals(null) && listOrderTransaction.size() > 0){
			logger.info(" Got an order transaction record "+listOrderTransaction.size()+" Reccords -- Let me do the processing" );
			//Got an Order Transaction in
			//Do the process
			orderTransaction = listOrderTransaction.get(0);
			
			Double  transactionTotal = ((sellOrder.getPrice() * sellOrder.getUnfulfilledquantity()) + (sellOrder.getPrice() * sellOrder.getUnfulfilledquantity() * FEEMULTIPLIER));
			Double transactionFee = (sellOrder.getPrice() * sellOrder.getUnfulfilledquantity() * FEEMULTIPLIER);
			//Increase the Transaction Order total
			orderTransaction.setTotal(orderTransaction.getTotal().doubleValue() + transactionTotal);
			orderTransaction.setFee(orderTransaction.getFee() + transactionFee);
			//Increase the transaction order quantity
			orderTransaction.setQuantity(orderTransaction.getQuantity().longValue() + sellOrder.getUnfulfilledquantity());
			
			//Set the sell order partial to it's quantity
			tradeOrder.setPartialquantity(sellOrder.getUnfulfilledquantity());
			//sellOrder.setUnfulfilledquantity(sellOrder.getUnfulfilledquantity().longValue() - tradeOrder.getUnfulfilledquantity());
			
			//Trade Order is the buy Order
			orderTransaction.getSourceOrderList().add(tradeOrder);
			
			orderTransactionService.update(orderTransaction);
			
			sellOrder.setOrderStatus(OrderStatus.CLOSED);
			tradeOrderDao.update(sellOrder);
			
		} else{
			logger.info("No Order transaction record !!!");
		}
	
	}

	public List<TradeOrder> findAllUnclosed(OrderStatus orderStatus,
			ExchangeUser exchangeUser) {
		return tradeOrderDao.findAllUnclosed(orderStatus, exchangeUser);
	}

	

}
