package org.exchangesystem.daoimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.exchangesystem.dao.TradeOrderDao;
import org.exchangesystem.model.OrderStatus;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TradeOrderDaoJpaImp extends AbstractJpaDao<TradeOrder> implements
		TradeOrderDao {

		@Autowired
		ExchangeSystemSession exchangeSystemSession;
		
		public TradeOrderDaoJpaImp(){
			setClazz(TradeOrder.class);
			setExchangeManagerSession(exchangeSystemSession);
		}

		/****
		 *Get open orders for the type specified - SELL, BUY
		 *for the specified status OPEN
		 *for the symbol specied, the currency in question 
		 ***/
		@Transactional
		public List<TradeOrder> findOpenBuyOrders(Symbol symbol,  OrderType orderType, OrderStatus orderStatus) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.symbol = :symbol) AND (torder.orderType = :orderType) AND (torder.orderStatus = :orderStatus)) ORDER BY price DESC ,  id ASC ");//.getResultList();
			q.setParameter("symbol", symbol);
			q.setParameter("orderType", orderType);
			q.setParameter("orderStatus", orderStatus);
			
			return q.getResultList();
		}

		/****
		 *Find open sell orders whose price are less than a given value 
		 ***/
		@Transactional
		public List<TradeOrder> findOpenSellOrders(Symbol symbol,
				OrderType orderType, OrderStatus orderStatus, Double price, Long quantity) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.symbol = :symbol) AND (torder.orderType = :orderType) AND (torder.orderStatus = :orderStatus) AND (torder.price <= :price) AND (torder.quantity = :quantity)) ORDER BY price ASC ,  id ASC ");//.getResultList();
			q.setParameter("symbol", symbol);
			q.setParameter("orderType", orderType);
			q.setParameter("orderStatus", orderStatus);
			q.setParameter("price", price);
			q.setParameter("quantity", quantity);
			
			return q.getResultList();
		}

		/***
		 * Get open buy orders - all that have not been processed so that the processing can happen
		 * **/
		@Transactional
		public List<TradeOrder> findOpenBuyOrders(OrderType orderType,
				OrderStatus orderStatus) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.orderType = :orderType) AND (torder.orderStatus = :orderStatus)) ORDER BY price DESC ,  id ASC ");//.getResultList();
			q.setParameter("orderType", orderType);
			q.setParameter("orderStatus", orderStatus);
			
			return q.getResultList();
		}

		@Transactional
		public List<TradeOrder> findAll(OrderStatus orderStatus) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.orderStatus = :orderStatus)) ORDER BY  id DESC ");//.getResultList();
			q.setParameter("orderStatus", orderStatus);
			
			return q.getResultList();
		}

		@Transactional
		public List<TradeOrder> findOpenSellOrdersNewestFirst(Symbol symbol,
				OrderType orderType, OrderStatus orderStatus) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.symbol = :symbol) AND (torder.orderType = :orderType) AND (torder.orderStatus = :orderStatus)) ORDER BY  id DESC ");//.getResultList();
			q.setParameter("symbol", symbol);
			q.setParameter("orderType", orderType);
			q.setParameter("orderStatus", orderStatus);
			
			return q.getResultList();
		}

		@Transactional
		public List<TradeOrder> findOpenBuyOrdersNewestFirst(Symbol symbol,
				OrderType orderType, OrderStatus orderStatus) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.symbol = :symbol) AND (torder.orderType = :orderType) AND (torder.orderStatus = :orderStatus)) ORDER BY  id DESC ");//.getResultList();
			q.setParameter("symbol", symbol);
			q.setParameter("orderType", orderType);
			q.setParameter("orderStatus", orderStatus);
			
			return q.getResultList();
		}

		@Transactional
		public List<TradeOrder> findOpenSellOrders(Symbol symbol,
				OrderType orderType, OrderStatus orderStatus, Double price) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.symbol = :symbol) AND (torder.orderType = :orderType) AND (torder.orderStatus = :orderStatus) AND (torder.price <= :price)) ORDER BY price ASC ,  id ASC ");//.getResultList();
			q.setParameter("symbol", symbol);
			q.setParameter("orderType", orderType);
			q.setParameter("orderStatus", orderStatus);
			q.setParameter("price", price);
			
			return q.getResultList();
		}

		/**
		 * Returns both the open and the partial Buy Orders for the symbol specified
		 * **/
		public List<TradeOrder> findOpenAndPartialBuyOrders(Symbol symbol,
				OrderType orderType, OrderStatus orderStatusOpen,
				OrderStatus orderStatusPartial) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.symbol = :symbol) AND (torder.orderType = :orderType) AND ((torder.orderStatus = :orderStatusOpen) OR (torder.orderStatus = :orderStatusPartial))) ORDER BY price DESC ,  id ASC ");//.getResultList();
			q.setParameter("symbol", symbol);
			q.setParameter("orderType", orderType);
			q.setParameter("orderStatusOpen", orderStatusOpen);
			q.setParameter("orderStatusPartial", orderStatusPartial);
			
			return q.getResultList();
		}

		/***
		 * Return both the Open and Partial Sell Orders for the symbol specified
		 * **/
		public List<TradeOrder> findOpenAndPartialSellOrders(Symbol symbol,
				OrderType orderType, OrderStatus orderStatus,
				OrderStatus orderStatusPartial, Double price) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.symbol = :symbol) AND (torder.orderType = :orderType) AND ((torder.orderStatus = :orderStatusOpen) OR (torder.orderStatus = :orderStatusPartial)) AND (torder.price <= :price)) ORDER BY price ASC ,  id ASC ");//.getResultList();
			q.setParameter("symbol", symbol);
			q.setParameter("orderType", orderType);
			q.setParameter("orderStatusOpen", orderStatus);
			q.setParameter("orderStatusPartial", orderStatusPartial);
			q.setParameter("price", price);
			
			return q.getResultList();
		}

		/**
		 * Return Partial and Open Buy Orders ordered newest first
		 * **/
		public List<TradeOrder> findOpenPartialBuyOrdersNewestFirst(
				Symbol symbol, OrderType orderType,
				OrderStatus orderStatusOpen, OrderStatus orderStatusPartial) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.symbol = :symbol) AND (torder.orderType = :orderType) AND ((torder.orderStatus = :orderStatusOpen) OR (torder.orderStatus = :orderStatusPartial))) ORDER BY  id DESC ");//.getResultList();
			q.setParameter("symbol", symbol);
			q.setParameter("orderType", orderType);
			q.setParameter("orderStatusOpen", orderStatusOpen);
			q.setParameter("orderStatusPartial", orderStatusPartial);
			
			
			return q.getResultList();
		}

		/**
		 * Return Open and Partial Sell Orders, Newest First
		 * **/
		public List<TradeOrder> findOpenPartialSellOrdersNewestFirst(
				Symbol symbol, OrderType orderType,
				OrderStatus orderStatusOpen, OrderStatus orderStatusPartial) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.symbol = :symbol) AND (torder.orderType = :orderType) AND ((torder.orderStatus = :orderStatusOpen) OR (torder.orderStatus = :orderStatusPartial))) ORDER BY  id DESC ");//.getResultList();
			q.setParameter("symbol", symbol);
			q.setParameter("orderType", orderType);
			q.setParameter("orderStatusOpen", orderStatusOpen);
			q.setParameter("orderStatusPartial", orderStatusPartial);
			
			return q.getResultList();
		}

		@Transactional
		public List<TradeOrder> findAllUnclosed(OrderStatus orderStatus) {
			EntityManager em  = super.entityManager;
			Query q = em.createQuery(" from TradeOrder torder WHERE ((torder.orderStatus <> :orderStatus)) ORDER BY  id DESC ");//.getResultList();
			q.setParameter("orderStatus", orderStatus);
			
			return q.getResultList();
		}
		
		
}
