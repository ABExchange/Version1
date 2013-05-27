package org.exchangesystem.web;

import java.util.List;

import org.exchangesystem.daoimp.ExchangeSystemSession;
import org.exchangesystem.dto.OrderSearch;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.OrderStatus;
import org.exchangesystem.model.OrderTransaction;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;
import org.exchangesystem.model.TradeStatus;
import org.exchangesystem.service.AccountService;
import org.exchangesystem.service.ExchangeUserService;
import org.exchangesystem.service.OrderTransactionService;
import org.exchangesystem.service.SymbolService;
import org.exchangesystem.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MarketController {
	
	@Autowired
	SymbolService symbolService;
	@Autowired
	TradeOrderService tradeOrderService;
	
	@Autowired
	OrderTransactionService orderTransactionService;
	
	@Autowired
	ExchangeSystemSession exchangeSystemSession;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ExchangeUserService userService;

	
	@RequestMapping(value={"/markets"}, method=RequestMethod.GET)
	public String market(Model model){
		
		//Get the Order Transactions for the dollar (USD)
		
		Symbol usdSymbol = symbolService.findSymbol("USD");
		List<OrderTransaction> listUSDTransaction = orderTransactionService.findAll(usdSymbol);

		//Get the Buy Order  Transactions
		//List<OrderTransaction> listUSDBuyOrderTransaction = orderTransactionService.findAll(usdSymbol, OrderType.BUY);
		//Get the sell order transactions
		//List<OrderTransaction> listUSDSellOrderTransaction = orderTransactionService.findAll(usdSymbol, OrderType.SELL);
	
		//Get USD Open Buy Orders
//		List<TradeOrder> listUSDBuyOrder = tradeOrderService.findOpenBuyOrdersNewestFirst(usdSymbol, OrderType.BUY, OrderStatus.OPEN); 
		//Get USD Open and Partial Buy Orders
		List<TradeOrder> listUSDBuyOrder = tradeOrderService.findOpenPartialBuyOrdersNewestFirst(usdSymbol, OrderType.BUY, OrderStatus.OPEN, OrderStatus.PARTIAL); 
		
		//Get USD Open Sell Orders
//		List<TradeOrder> listUSDSellOrder = tradeOrderService.findOpenSellOrdersNewestFirst(usdSymbol, OrderType.SELL, OrderStatus.OPEN);
		//Get USD Open and Partial Sell Orders
		List<TradeOrder> listUSDSellOrder = tradeOrderService.findOpenPartialSellOrdersNewestFirst(usdSymbol, OrderType.SELL, OrderStatus.OPEN, OrderStatus.PARTIAL);
	
		//Get the Order Transactions for the CAD
		Symbol cadSymbol = symbolService.findSymbol("CAD");
		List<OrderTransaction> listCADTransaction = orderTransactionService.findAll(cadSymbol);
		
		//Get the Buy Order  Transactions
		//List<OrderTransaction> listCADBuyOrderTransaction = orderTransactionService.findAll(cadSymbol, OrderType.BUY);
		//Get the sell order transactions
		//List<OrderTransaction> listCADSellOrderTransaction = orderTransactionService.findAll(cadSymbol, OrderType.SELL);
		
		//Get CAD Open Buy Order
//		List<TradeOrder> listCADBuyOrder = tradeOrderService.findOpenBuyOrdersNewestFirst(cadSymbol, OrderType.BUY, OrderStatus.OPEN);
		//Get CAD Open amd Partial Buy Order
		List<TradeOrder> listCADBuyOrder = tradeOrderService.findOpenPartialBuyOrdersNewestFirst(cadSymbol, OrderType.BUY, OrderStatus.OPEN, OrderStatus.PARTIAL);
		
		//Get CAD Open Sell Order
//		List<TradeOrder> listCADSellOrder = tradeOrderService.findOpenSellOrdersNewestFirst(cadSymbol, OrderType.SELL, OrderStatus.OPEN);
		//Get CAD Open Sell Order
		List<TradeOrder> listCADSellOrder = tradeOrderService.findOpenPartialSellOrdersNewestFirst(cadSymbol, OrderType.SELL, OrderStatus.OPEN, OrderStatus.PARTIAL);
	
		List<OrderTransaction> listOrderTransaction = orderTransactionService.findAllClosed(OrderStatus.CLOSED);
				
				//orderTransactionService.findAll();
		model.addAttribute("listOrderTransaction", listOrderTransaction);

		
		model.addAttribute("user", exchangeSystemSession.getUser());

		model.addAttribute("listUSDTransaction", listUSDTransaction);
		model.addAttribute("listUSDBuyOrder", listUSDBuyOrder);
		model.addAttribute("listUSDSellOrder", listUSDSellOrder);
		
		
		model.addAttribute("listCADTransaction", listCADTransaction);
		model.addAttribute("listCADBuyOrder", listCADBuyOrder);
		model.addAttribute("listCADSellOrder", listCADSellOrder);
		
		//List<TradeOrder> listOrder = tradeOrderService.findAll(OrderStatus.OPEN);
		//model.addAttribute("listOrder", listOrder);
		
		//Market Values Listing
		List<Symbol> listSymbol = symbolService.findAll();
		TradeStatus tradeStatus = exchangeSystemSession.getTradeStatus();
		Symbol defaultSymbol = tradeStatus.getSymbol();//symbolService.findSymbol("USD");
		//tradeStatus.setSymbol(defaultSymbol);
		model.addAttribute("listSymbol", listSymbol);
		
		model.addAttribute("tradeStatus", tradeStatus);
		//Default Symbol - USD
		model.addAttribute("defaultSymbol", defaultSymbol);
		//Last Price
		Double lastPrice = symbolService.getLastPrice(defaultSymbol);
		model.addAttribute("lastPrice", lastPrice);
		//High
		Double highPrice = symbolService.getHighPrice(defaultSymbol);
		model.addAttribute("highPrice", highPrice);
		//Low
		Double lowPrice = symbolService.getLowPrice(defaultSymbol);
		model.addAttribute("lowPrice", lowPrice);
		//Volume
		Long volume = symbolService.getVolume(defaultSymbol);
		model.addAttribute("volume", volume);
		//AVG
		Double average = symbolService.getAverage(defaultSymbol);
		model.addAttribute("average", average);
		
		model.addAttribute("accountNo", (exchangeSystemSession.getUser().getAccountNumber() != null) ? exchangeSystemSession.getUser().getAccountNumber() : "");
	
		
		Symbol currencySymbol = symbolService.findSymbol("HKD");
		Double hkdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("RMB");
		Double rmbBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("USD");
		Double usdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("BTC");
		Double btcBalance = accountService.getBalance(currencySymbol);
		
		model.addAttribute("hkdBalance", hkdBalance);
		model.addAttribute("rmbBalance", rmbBalance);
		model.addAttribute("usdBalance", usdBalance);
		model.addAttribute("btcBalance", btcBalance);

		return "market";
	}
	
	@RequestMapping(value={"/systemorderstransactions"}, method=RequestMethod.GET)
	public String systemorderstransactions(Model model){
		
		//Get the Order Transactions for the dollar (USD)
		
		Symbol usdSymbol = symbolService.findSymbol("USD");
		List<OrderTransaction> listUSDTransaction = orderTransactionService.findAll(usdSymbol);

		//Get the Buy Order  Transactions
		//List<OrderTransaction> listUSDBuyOrderTransaction = orderTransactionService.findAll(usdSymbol, OrderType.BUY);
		//Get the sell order transactions
		//List<OrderTransaction> listUSDSellOrderTransaction = orderTransactionService.findAll(usdSymbol, OrderType.SELL);
	
		//Get USD Open Buy Orders
//		List<TradeOrder> listUSDBuyOrder = tradeOrderService.findOpenBuyOrdersNewestFirst(usdSymbol, OrderType.BUY, OrderStatus.OPEN); 
		//Get USD Open and Partial Buy Orders
		List<TradeOrder> listUSDBuyOrder = tradeOrderService.findOpenPartialBuyOrdersNewestFirst(usdSymbol, OrderType.BUY, OrderStatus.OPEN, OrderStatus.PARTIAL); 
		
		//Get USD Open Sell Orders
//		List<TradeOrder> listUSDSellOrder = tradeOrderService.findOpenSellOrdersNewestFirst(usdSymbol, OrderType.SELL, OrderStatus.OPEN);
		//Get USD Open and Partial Sell Orders
		List<TradeOrder> listUSDSellOrder = tradeOrderService.findOpenPartialSellOrdersNewestFirst(usdSymbol, OrderType.SELL, OrderStatus.OPEN, OrderStatus.PARTIAL);
	
		//Get the Order Transactions for the CAD
		Symbol cadSymbol = symbolService.findSymbol("CAD");
		List<OrderTransaction> listCADTransaction = orderTransactionService.findAll(cadSymbol);
		
		//Get the Buy Order  Transactions
		//List<OrderTransaction> listCADBuyOrderTransaction = orderTransactionService.findAll(cadSymbol, OrderType.BUY);
		//Get the sell order transactions
		//List<OrderTransaction> listCADSellOrderTransaction = orderTransactionService.findAll(cadSymbol, OrderType.SELL);
		
		//Get CAD Open Buy Order
//		List<TradeOrder> listCADBuyOrder = tradeOrderService.findOpenBuyOrdersNewestFirst(cadSymbol, OrderType.BUY, OrderStatus.OPEN);
		//Get CAD Open amd Partial Buy Order
		List<TradeOrder> listCADBuyOrder = tradeOrderService.findOpenPartialBuyOrdersNewestFirst(cadSymbol, OrderType.BUY, OrderStatus.OPEN, OrderStatus.PARTIAL);
		
		//Get CAD Open Sell Order
//		List<TradeOrder> listCADSellOrder = tradeOrderService.findOpenSellOrdersNewestFirst(cadSymbol, OrderType.SELL, OrderStatus.OPEN);
		//Get CAD Open Sell Order
		List<TradeOrder> listCADSellOrder = tradeOrderService.findOpenPartialSellOrdersNewestFirst(cadSymbol, OrderType.SELL, OrderStatus.OPEN, OrderStatus.PARTIAL);
	
		List<OrderTransaction> listOrderTransaction = orderTransactionService.findAll();//findAllClosed(OrderStatus.CLOSED);
				
				//orderTransactionService.findAll();
		model.addAttribute("listOrderTransaction", listOrderTransaction);

		
		model.addAttribute("user", exchangeSystemSession.getUser());

		model.addAttribute("listUSDTransaction", listUSDTransaction);
		model.addAttribute("listUSDBuyOrder", listUSDBuyOrder);
		model.addAttribute("listUSDSellOrder", listUSDSellOrder);
		
		
		model.addAttribute("listCADTransaction", listCADTransaction);
		model.addAttribute("listCADBuyOrder", listCADBuyOrder);
		model.addAttribute("listCADSellOrder", listCADSellOrder);
		
		//List<TradeOrder> listOrder = tradeOrderService.findAll(OrderStatus.OPEN);
		//model.addAttribute("listOrder", listOrder);
		
		//Market Values Listing
		List<Symbol> listSymbol = symbolService.findAll();
		TradeStatus tradeStatus = exchangeSystemSession.getTradeStatus();
		Symbol defaultSymbol = tradeStatus.getSymbol();//symbolService.findSymbol("USD");
		//tradeStatus.setSymbol(defaultSymbol);
		model.addAttribute("listSymbol", listSymbol);
		
		model.addAttribute("tradeStatus", tradeStatus);
		//Default Symbol - USD
		model.addAttribute("defaultSymbol", defaultSymbol);
		//Last Price
		Double lastPrice = symbolService.getLastPrice(defaultSymbol);
		model.addAttribute("lastPrice", lastPrice);
		//High
		Double highPrice = symbolService.getHighPrice(defaultSymbol);
		model.addAttribute("highPrice", highPrice);
		//Low
		Double lowPrice = symbolService.getLowPrice(defaultSymbol);
		model.addAttribute("lowPrice", lowPrice);
		//Volume
		Long volume = symbolService.getVolume(defaultSymbol);
		model.addAttribute("volume", volume);
		//AVG
		Double average = symbolService.getAverage(defaultSymbol);
		model.addAttribute("average", average);
		
		model.addAttribute("accountNo", (exchangeSystemSession.getUser().getAccountNumber() != null) ? exchangeSystemSession.getUser().getAccountNumber() : "");
	
		
		Symbol currencySymbol = symbolService.findSymbol("HKD");
		Double hkdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("RMB");
		Double rmbBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("USD");
		Double usdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("BTC");
		Double btcBalance = accountService.getBalance(currencySymbol);
		
		model.addAttribute("hkdBalance", hkdBalance);
		model.addAttribute("rmbBalance", rmbBalance);
		model.addAttribute("usdBalance", usdBalance);
		model.addAttribute("btcBalance", btcBalance);

		return "systemorderstransactions";
	}
	
	
	@RequestMapping(value={"/systemorders"}, method=RequestMethod.GET)
	public String systemorders(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
		
		//Get the Order Transactions for the dollar (USD)
		
		Symbol usdSymbol = symbolService.findSymbol("USD");
		List<OrderTransaction> listUSDTransaction = orderTransactionService.findAll(usdSymbol);

		//Get the Buy Order  Transactions
		//List<OrderTransaction> listUSDBuyOrderTransaction = orderTransactionService.findAll(usdSymbol, OrderType.BUY);
		//Get the sell order transactions
		//List<OrderTransaction> listUSDSellOrderTransaction = orderTransactionService.findAll(usdSymbol, OrderType.SELL);
	
		//Get USD Open Buy Orders
//		List<TradeOrder> listUSDBuyOrder = tradeOrderService.findOpenBuyOrdersNewestFirst(usdSymbol, OrderType.BUY, OrderStatus.OPEN); 
		//Get USD Open and Partial Buy Orders
		List<TradeOrder> listUSDBuyOrder = tradeOrderService.findOpenPartialBuyOrdersNewestFirst(usdSymbol, OrderType.BUY, OrderStatus.OPEN, OrderStatus.PARTIAL); 
		
		//Get USD Open Sell Orders
//		List<TradeOrder> listUSDSellOrder = tradeOrderService.findOpenSellOrdersNewestFirst(usdSymbol, OrderType.SELL, OrderStatus.OPEN);
		//Get USD Open and Partial Sell Orders
		List<TradeOrder> listUSDSellOrder = tradeOrderService.findOpenPartialSellOrdersNewestFirst(usdSymbol, OrderType.SELL, OrderStatus.OPEN, OrderStatus.PARTIAL);
	
		//Get the Order Transactions for the CAD
		Symbol cadSymbol = symbolService.findSymbol("CAD");
		List<OrderTransaction> listCADTransaction = orderTransactionService.findAll(cadSymbol);
		
		//Get the Buy Order  Transactions
		//List<OrderTransaction> listCADBuyOrderTransaction = orderTransactionService.findAll(cadSymbol, OrderType.BUY);
		//Get the sell order transactions
		//List<OrderTransaction> listCADSellOrderTransaction = orderTransactionService.findAll(cadSymbol, OrderType.SELL);
		
		//Get CAD Open Buy Order
//		List<TradeOrder> listCADBuyOrder = tradeOrderService.findOpenBuyOrdersNewestFirst(cadSymbol, OrderType.BUY, OrderStatus.OPEN);
		//Get CAD Open amd Partial Buy Order
		List<TradeOrder> listCADBuyOrder = tradeOrderService.findOpenPartialBuyOrdersNewestFirst(cadSymbol, OrderType.BUY, OrderStatus.OPEN, OrderStatus.PARTIAL);
		
		//Get CAD Open Sell Order
//		List<TradeOrder> listCADSellOrder = tradeOrderService.findOpenSellOrdersNewestFirst(cadSymbol, OrderType.SELL, OrderStatus.OPEN);
		//Get CAD Open Sell Order
		List<TradeOrder> listCADSellOrder = tradeOrderService.findOpenPartialSellOrdersNewestFirst(cadSymbol, OrderType.SELL, OrderStatus.OPEN, OrderStatus.PARTIAL);
	
		//List<OrderTransaction> listOrderTransaction = orderTransactionService.findAll();//findAllClosed(OrderStatus.CLOSED);
		List<TradeOrder> listTradeOrders = tradeOrderService.findAll();		
				//orderTransactionService.findAll();
		model.addAttribute("listTradeOrders", listTradeOrders);

		
		model.addAttribute("user", exchangeSystemSession.getUser());

		model.addAttribute("listUSDTransaction", listUSDTransaction);
		model.addAttribute("listUSDBuyOrder", listUSDBuyOrder);
		model.addAttribute("listUSDSellOrder", listUSDSellOrder);
		
		
		model.addAttribute("listCADTransaction", listCADTransaction);
		model.addAttribute("listCADBuyOrder", listCADBuyOrder);
		model.addAttribute("listCADSellOrder", listCADSellOrder);
		
		//List<TradeOrder> listOrder = tradeOrderService.findAll(OrderStatus.OPEN);
		//model.addAttribute("listOrder", listOrder);
		
		//Market Values Listing
		List<Symbol> listSymbol = symbolService.findAll();
		TradeStatus tradeStatus = exchangeSystemSession.getTradeStatus();
		Symbol defaultSymbol = tradeStatus.getSymbol();//symbolService.findSymbol("USD");
		//tradeStatus.setSymbol(defaultSymbol);
		model.addAttribute("listSymbol", listSymbol);
		
		model.addAttribute("tradeStatus", tradeStatus);
		//Default Symbol - USD
		model.addAttribute("defaultSymbol", defaultSymbol);
		//Last Price
		Double lastPrice = symbolService.getLastPrice(defaultSymbol);
		model.addAttribute("lastPrice", lastPrice);
		//High
		Double highPrice = symbolService.getHighPrice(defaultSymbol);
		model.addAttribute("highPrice", highPrice);
		//Low
		Double lowPrice = symbolService.getLowPrice(defaultSymbol);
		model.addAttribute("lowPrice", lowPrice);
		//Volume
		Long volume = symbolService.getVolume(defaultSymbol);
		model.addAttribute("volume", volume);
		//AVG
		Double average = symbolService.getAverage(defaultSymbol);
		model.addAttribute("average", average);
		
		model.addAttribute("accountNo", (exchangeSystemSession.getUser().getAccountNumber() != null) ? exchangeSystemSession.getUser().getAccountNumber() : "");
	
		
		Symbol currencySymbol = symbolService.findSymbol("HKD");
		Double hkdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("RMB");
		Double rmbBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("USD");
		Double usdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("BTC");
		Double btcBalance = accountService.getBalance(currencySymbol);
		
		model.addAttribute("hkdBalance", hkdBalance);
		model.addAttribute("rmbBalance", rmbBalance);
		model.addAttribute("usdBalance", usdBalance);
		model.addAttribute("btcBalance", btcBalance);
		
		List<ExchangeUser> usersList = userService.findAll();
		model.addAttribute("usersList", usersList);
		List<Symbol> symbolsList = symbolService.findAll();
		model.addAttribute("symbolsList", symbolsList);
		

		return "systemorders";
	}
	
	@RequestMapping(value={"/systemorders"}, method=RequestMethod.POST)
	public String systemorderspost(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
		
		//Get the Order Transactions for the dollar (USD)
		
		Symbol usdSymbol = symbolService.findSymbol("USD");
		List<OrderTransaction> listUSDTransaction = orderTransactionService.findAll(usdSymbol);

		//Get the Buy Order  Transactions
		//List<OrderTransaction> listUSDBuyOrderTransaction = orderTransactionService.findAll(usdSymbol, OrderType.BUY);
		//Get the sell order transactions
		//List<OrderTransaction> listUSDSellOrderTransaction = orderTransactionService.findAll(usdSymbol, OrderType.SELL);
	
		//Get USD Open Buy Orders
//		List<TradeOrder> listUSDBuyOrder = tradeOrderService.findOpenBuyOrdersNewestFirst(usdSymbol, OrderType.BUY, OrderStatus.OPEN); 
		//Get USD Open and Partial Buy Orders
		List<TradeOrder> listUSDBuyOrder = tradeOrderService.findOpenPartialBuyOrdersNewestFirst(usdSymbol, OrderType.BUY, OrderStatus.OPEN, OrderStatus.PARTIAL); 
		
		//Get USD Open Sell Orders
//		List<TradeOrder> listUSDSellOrder = tradeOrderService.findOpenSellOrdersNewestFirst(usdSymbol, OrderType.SELL, OrderStatus.OPEN);
		//Get USD Open and Partial Sell Orders
		List<TradeOrder> listUSDSellOrder = tradeOrderService.findOpenPartialSellOrdersNewestFirst(usdSymbol, OrderType.SELL, OrderStatus.OPEN, OrderStatus.PARTIAL);
	
		//Get the Order Transactions for the CAD
		Symbol cadSymbol = symbolService.findSymbol("CAD");
		List<OrderTransaction> listCADTransaction = orderTransactionService.findAll(cadSymbol);
		
		//Get the Buy Order  Transactions
		//List<OrderTransaction> listCADBuyOrderTransaction = orderTransactionService.findAll(cadSymbol, OrderType.BUY);
		//Get the sell order transactions
		//List<OrderTransaction> listCADSellOrderTransaction = orderTransactionService.findAll(cadSymbol, OrderType.SELL);
		
		//Get CAD Open Buy Order
//		List<TradeOrder> listCADBuyOrder = tradeOrderService.findOpenBuyOrdersNewestFirst(cadSymbol, OrderType.BUY, OrderStatus.OPEN);
		//Get CAD Open amd Partial Buy Order
		List<TradeOrder> listCADBuyOrder = tradeOrderService.findOpenPartialBuyOrdersNewestFirst(cadSymbol, OrderType.BUY, OrderStatus.OPEN, OrderStatus.PARTIAL);
		
		//Get CAD Open Sell Order
//		List<TradeOrder> listCADSellOrder = tradeOrderService.findOpenSellOrdersNewestFirst(cadSymbol, OrderType.SELL, OrderStatus.OPEN);
		//Get CAD Open Sell Order
		List<TradeOrder> listCADSellOrder = tradeOrderService.findOpenPartialSellOrdersNewestFirst(cadSymbol, OrderType.SELL, OrderStatus.OPEN, OrderStatus.PARTIAL);
	
		//List<OrderTransaction> listOrderTransaction = orderTransactionService.findAll();//findAllClosed(OrderStatus.CLOSED);
		
		//Find Orders based on Symbol and User
		List<TradeOrder> listTradeOrders;
		
		
		//orderSearch.setSymbol(symbolService.findSymbol(orderSearch.getSymbol().getCode()));
		//orderSearch.setCreatedBy(userService.findById(orderSearch.getCreatedBy().getId()));
		
	
		System.out.println("FFFFFFThe Symbol is ::: "+orderSearch.getSymbol().getId());
		System.out.println("FFFFFFFFThe User is ::: "+orderSearch.getCreatedBy().getId());
		if ((orderSearch.getSymbol().getId() != -1) && (orderSearch.getCreatedBy().getId() != -1)){
			//all Orders for symbol and user
			listTradeOrders = tradeOrderService.findAll(orderSearch.getSymbol(), orderSearch.getCreatedBy());
		} else if (orderSearch.getSymbol().getId() != -1){
			//Get Orders for given Symbol
			listTradeOrders = tradeOrderService.findAll(orderSearch.getSymbol());
		} else if (orderSearch.getCreatedBy().getId() != -1){
			//Get Orders for User
			listTradeOrders = tradeOrderService.findAll(orderSearch.getCreatedBy());
		} else{
			//Get all Orders
			listTradeOrders = tradeOrderService.findAll();
		}
		
		 		
				//orderTransactionService.findAll();
		model.addAttribute("listTradeOrders", listTradeOrders);

		
		model.addAttribute("user", exchangeSystemSession.getUser());

		model.addAttribute("listUSDTransaction", listUSDTransaction);
		model.addAttribute("listUSDBuyOrder", listUSDBuyOrder);
		model.addAttribute("listUSDSellOrder", listUSDSellOrder);
		
		
		model.addAttribute("listCADTransaction", listCADTransaction);
		model.addAttribute("listCADBuyOrder", listCADBuyOrder);
		model.addAttribute("listCADSellOrder", listCADSellOrder);
		
		//List<TradeOrder> listOrder = tradeOrderService.findAll(OrderStatus.OPEN);
		//model.addAttribute("listOrder", listOrder);
		
		//Market Values Listing
		List<Symbol> listSymbol = symbolService.findAll();
		TradeStatus tradeStatus = exchangeSystemSession.getTradeStatus();
		Symbol defaultSymbol = tradeStatus.getSymbol();//symbolService.findSymbol("USD");
		//tradeStatus.setSymbol(defaultSymbol);
		model.addAttribute("listSymbol", listSymbol);
		
		model.addAttribute("tradeStatus", tradeStatus);
		//Default Symbol - USD
		model.addAttribute("defaultSymbol", defaultSymbol);
		//Last Price
		Double lastPrice = symbolService.getLastPrice(defaultSymbol);
		model.addAttribute("lastPrice", lastPrice);
		//High
		Double highPrice = symbolService.getHighPrice(defaultSymbol);
		model.addAttribute("highPrice", highPrice);
		//Low
		Double lowPrice = symbolService.getLowPrice(defaultSymbol);
		model.addAttribute("lowPrice", lowPrice);
		//Volume
		Long volume = symbolService.getVolume(defaultSymbol);
		model.addAttribute("volume", volume);
		//AVG
		Double average = symbolService.getAverage(defaultSymbol);
		model.addAttribute("average", average);
		
		model.addAttribute("accountNo", (exchangeSystemSession.getUser().getAccountNumber() != null) ? exchangeSystemSession.getUser().getAccountNumber() : "");
	
		
		Symbol currencySymbol = symbolService.findSymbol("HKD");
		Double hkdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("RMB");
		Double rmbBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("USD");
		Double usdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("BTC");
		Double btcBalance = accountService.getBalance(currencySymbol);
		
		model.addAttribute("hkdBalance", hkdBalance);
		model.addAttribute("rmbBalance", rmbBalance);
		model.addAttribute("usdBalance", usdBalance);
		model.addAttribute("btcBalance", btcBalance);
		
		List<ExchangeUser> usersList = userService.findAll();
		model.addAttribute("usersList", usersList);
		List<Symbol> symbolsList = symbolService.findAll();
		model.addAttribute("symbolsList", symbolsList);
		

		return "systemorders";
	}

}
