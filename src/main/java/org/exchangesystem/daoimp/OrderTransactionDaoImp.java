package org.exchangesystem.daoimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.exchangesystem.dao.OrderTransactionDao;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.OrderStatus;
import org.exchangesystem.model.OrderTransaction;
import org.exchangesystem.model.OrderTransactionType;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderTransactionDaoImp extends AbstractJpaDao<OrderTransaction>
		implements OrderTransactionDao {

	@Autowired
	ExchangeSystemSession exchangeManagerSession;

	
	public OrderTransactionDaoImp(){
		setClazz(OrderTransaction.class);
		setExchangeManagerSession(exchangeManagerSession);
		
	}


	@Transactional
	public List<OrderTransaction> findAll(Symbol symbol) {
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" from OrderTransaction ordertrx WHERE ((ordertrx.symbol = :symbol)) ORDER BY  id DESC ");// .getResultList();
		q.setParameter("symbol", symbol);

		return q.getResultList();
	}


	@Transactional
	public List<OrderTransaction> findAll(Symbol symbol, OrderType orderType) {
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" from OrderTransaction ordertrx WHERE ((ordertrx.symbol = :symbol) AND (ordertrx.orderType = :orderType)) ORDER BY  id DESC ");// .getResultList();
		q.setParameter("symbol", symbol);
		q.setParameter("orderType", orderType);

		return q.getResultList();
	}

//transactionType
	@Transactional
	public List<OrderTransaction> findAll(TradeOrder tradeOrder, Symbol symbol, OrderTransactionType transactionType) {
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" from OrderTransaction ordertrx WHERE ((ordertrx.tradeOrder = :tradeOrder) AND (ordertrx.symbol = :symbol) AND (ordertrx.transactionType = :transactionType)) ORDER BY  id DESC ");// .getResultList();
		q.setParameter("tradeOrder", tradeOrder);
		q.setParameter("symbol", symbol);
		q.setParameter("transactionType", transactionType);

		return q.getResultList();
	}

	@Transactional
	public List<OrderTransaction> findAll(TradeOrder tradeOrder, Symbol symbol) {
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" from OrderTransaction ordertrx WHERE ((ordertrx.tradeOrder = :tradeOrder) AND (ordertrx.symbol = :symbol)) ORDER BY  id DESC ");// .getResultList();
		q.setParameter("tradeOrder", tradeOrder);
		q.setParameter("symbol", symbol);

		return q.getResultList();
	}


	@Transactional
	public List<OrderTransaction> findAll(ExchangeUser exchangeUser) {
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" from OrderTransaction ordertrx WHERE ((ordertrx.createdBy = :exchangeUser) OR ((ordertrx.updatedBy = :updateUser))) ORDER BY  id DESC ");// .getResultList();
		q.setParameter("exchangeUser", exchangeUser);
		q.setParameter("updateUser", exchangeUser);

		return q.getResultList();
	}


	@Transactional
	public List<OrderTransaction> findAllClosed(OrderStatus orderStatus) {
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" from OrderTransaction ordertrx WHERE (ordertrx.tradeOrder.orderStatus = :orderStatus) ORDER BY  ordertrx.id DESC ");// .getResultList();
		q.setParameter("orderStatus", OrderStatus.CLOSED);

		return q.getResultList();
	}


}
