package org.exchangesystem.dao;

import java.util.List;

import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.OrderStatus;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;

public interface TradeOrderDao extends Dao<TradeOrder> {
	public List<TradeOrder> findOpenBuyOrders(Symbol symbol,  OrderType orderType, OrderStatus orderStatus);
	public List<TradeOrder> findOpenBuyOrdersNewestFirst(Symbol symbol,  OrderType orderType, OrderStatus orderStatus);
	public List<TradeOrder> findOpenSellOrders(Symbol symbol,  OrderType orderType, OrderStatus orderStatus, Double price, Long quantity);
	public List<TradeOrder> findOpenSellOrdersNewestFirst(Symbol symbol,  OrderType orderType, OrderStatus orderStatus);
	public List<TradeOrder> findOpenBuyOrders(OrderType orderType, OrderStatus orderStatus);
	public List<TradeOrder> findAll(OrderStatus orderStatus);

	public List<TradeOrder> findAllUnclosed(OrderStatus orderStatus);
	public List<TradeOrder> findAllUnclosed(OrderStatus orderStatus, ExchangeUser exchangeUser);

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

	public List<TradeOrder> findAll(Symbol symbol, ExchangeUser exchangeUser);
	public List<TradeOrder> findAll(Symbol symbol);
	public List<TradeOrder> findAll(ExchangeUser exchangeUser);

}
