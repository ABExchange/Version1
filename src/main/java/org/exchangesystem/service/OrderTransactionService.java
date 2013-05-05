package org.exchangesystem.service;

import java.util.List;

import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.OrderTransaction;
import org.exchangesystem.model.OrderTransactionType;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;

public interface OrderTransactionService extends BaseService<OrderTransaction> {
	public List<OrderTransaction> findAll(ExchangeUser exchangeUser);
	public List<OrderTransaction> findAll(Symbol symbol);
	public List<OrderTransaction> findAll(Symbol symbol, OrderType orderType);
	
	/***
	 * Purposefully to get aggregation candidate Orders
	 * **/
	public List<OrderTransaction> findAll(TradeOrder tradeOrder, Symbol symbol, OrderTransactionType transactionType);

	public List<OrderTransaction> findAll(TradeOrder tradeOrder, Symbol symbol);

}
