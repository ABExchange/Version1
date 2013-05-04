package org.exchangesystem.service;

import java.util.List;

import org.exchangesystem.model.OrderStatus;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;

public interface TradeOrderService extends BaseService<TradeOrder> {

	public List<TradeOrder> findOpenBuyOrders(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus);

	public List<TradeOrder> findOpenSellOrders(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus, Double price,
			Long quantity);

	public List<TradeOrder> findOpenBuyOrders(OrderType orderType,
			OrderStatus orderStatus);

	public void executePendingOrders();

	public List<TradeOrder> findAll(OrderStatus orderStatus);

	public List<TradeOrder> findOpenSellOrdersNewestFirst(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus);

	public List<TradeOrder> findOpenBuyOrdersNewestFirst(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus);
	
	public List<TradeOrder> findOpenSellOrders(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus, Double price);
	
	
	/**
	 *Newest first Buy and Sell Orders both open and partial 
	 ***/
	public List<TradeOrder> findOpenPartialBuyOrdersNewestFirst(Symbol symbol,  OrderType orderType, OrderStatus orderStatusOpen, OrderStatus orderStatusPartial);
	public List<TradeOrder> findOpenPartialSellOrdersNewestFirst(Symbol symbol,  OrderType orderType, OrderStatus orderStatusOpen, OrderStatus orderStatusPartial);

	
	/***
	 * Includes the Partial in the Buy and Sell Order Listing
	 * **/
	public List<TradeOrder> findOpenAndPartialBuyOrders(Symbol symbol,  OrderType orderType, OrderStatus orderStatusOpen, OrderStatus orderStatusPartial);
	public List<TradeOrder> findOpenAndPartialSellOrders(Symbol symbol,
			OrderType orderType, OrderStatus orderStatus, OrderStatus orderStatusPartial, Double price);

	/***
	 * Get all the Orders that are not closed
	 * **/
	public List<TradeOrder> findAllUnclosed(OrderStatus orderStatus);

}
