package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.OrderTransactionDao;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.OrderTransaction;
import org.exchangesystem.model.OrderTransactionType;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;
import org.exchangesystem.service.OrderTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderTransactionServiceImp implements OrderTransactionService {

	@Autowired
	OrderTransactionDao orderTransactionDao;
	
	public void add(OrderTransaction orderTransaction) {
		orderTransactionDao.save(orderTransaction);
	}

	public List<OrderTransaction> findAll() {
		return orderTransactionDao.findAll();
	}

	public OrderTransaction findById(Long id) {
		return orderTransactionDao.findOne(id);
	}

	public void remove(OrderTransaction orderTransaction) {
		orderTransactionDao.delete(orderTransaction);
	}

	public void update(OrderTransaction orderTransaction) {
		orderTransactionDao.update(orderTransaction);
	}

	public Long count() {
		return orderTransactionDao.count();
	}

	public List<OrderTransaction> findAll(Symbol symbol) {
		return orderTransactionDao.findAll(symbol);
	}

	public List<OrderTransaction> findAll(Symbol symbol, OrderType orderType) {
		return orderTransactionDao.findAll(symbol, orderType);
	}

	public List<OrderTransaction> findAll(TradeOrder tradeOrder, Symbol symbol,
			OrderTransactionType transactionType) {
		return orderTransactionDao.findAll(tradeOrder, symbol, transactionType);
	}

	public List<OrderTransaction> findAll(TradeOrder tradeOrder, Symbol symbol) {
		return orderTransactionDao.findAll(tradeOrder, symbol);
	}

	public List<OrderTransaction> findAll(ExchangeUser exchangeUser) {
		return orderTransactionDao.findAll(exchangeUser);
	}

}

