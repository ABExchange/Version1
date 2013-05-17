package org.exchangesystem.daoimp;

import javax.persistence.Query;

import org.exchangesystem.dao.SymbolDao;
import org.exchangesystem.model.OrderStatus;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SymbolDaoJpaImp extends AbstractJpaDao<Symbol> implements
		SymbolDao {
	
	
	public SymbolDaoJpaImp(){
		setClazz(Symbol.class);
		setExchangeManagerSession(exchangeManagerSession);
		
	}
	
	@Autowired
	ExchangeSystemSession exchangeManagerSession;

	
	
	@Transactional
	public Symbol findSymbol(String code){
		Symbol symbol = null;
		Query q = super.entityManager.createQuery(" SELECT s from  Symbol s WHERE (s.code = :code) ");
		q.setParameter("code", code);
		
		if (!q.getResultList().isEmpty())
			symbol = (Symbol)q.getResultList().get(0);
		
		return symbol;
	}



	/**
	 * New Methods
	 * **/
	
	@Transactional
	public Double getLastPrice(Symbol symbol) {
		TradeOrder tradeOrder = null;
		Query q = super.entityManager.createQuery(" SELECT tOrder from  TradeOrder tOrder WHERE ((tOrder.symbol = :symbol) AND (DATE(tOrder.created) = CURDATE())  AND (tOrder.orderType = :orderType) AND ((tOrder.orderStatus = :orderStatusOpen) OR (tOrder.orderStatus = :orderStatusPartial))) ORDER BY tOrder.id DESC");
		q.setParameter("symbol", symbol);
		q.setParameter("orderType", OrderType.SELL);
		q.setParameter("orderStatusOpen", OrderStatus.OPEN);
		q.setParameter("orderStatusPartial", OrderStatus.PARTIAL);
		q.setMaxResults(1);
		if (!q.getResultList().isEmpty())
			tradeOrder = (TradeOrder)q.getResultList().get(0);
		
		Double lastPrice = 0d;
		if (!(tradeOrder == null)){
			lastPrice = tradeOrder.getPrice();
		}
		
		return lastPrice;
		
	}



	/**
	 * Highest price
	 * **/
	@Transactional
	public Double getHighPrice(Symbol symbol) {
		TradeOrder tradeOrder = null;
		Query q = super.entityManager.createQuery(" SELECT tOrder from  TradeOrder tOrder WHERE ((tOrder.symbol = :symbol) AND (DATE(tOrder.created) = CURDATE()) AND (tOrder.orderType = :orderType) AND ((tOrder.orderStatus = :orderStatusOpen) OR (tOrder.orderStatus = :orderStatusPartial))) ORDER BY tOrder.price DESC");
		q.setParameter("symbol", symbol);
		q.setParameter("orderType", OrderType.SELL);
		q.setParameter("orderStatusOpen", OrderStatus.OPEN);
		q.setParameter("orderStatusPartial", OrderStatus.PARTIAL);
		q.setMaxResults(1);
		
		if (!q.getResultList().isEmpty())
			tradeOrder = (TradeOrder)q.getResultList().get(0);
		
		Double highPrice = 0d;
		if (!(tradeOrder == null)){
			highPrice = tradeOrder.getPrice();
		}
		
		return highPrice;	
	}



	/**
	 * Lowest Price
	 * **/
	@Transactional
	public Double getLowPrice(Symbol symbol) {
		TradeOrder tradeOrder = null;
		Query q = super.entityManager.createQuery(" SELECT tOrder from  TradeOrder tOrder WHERE ((tOrder.symbol = :symbol) AND (DATE(tOrder.created) = CURDATE())  AND (tOrder.orderType = :orderType) AND ((tOrder.orderStatus = :orderStatusOpen) OR (tOrder.orderStatus = :orderStatusPartial))) ORDER BY tOrder.price ");
		q.setParameter("symbol", symbol);
		q.setParameter("orderType", OrderType.SELL);
		q.setParameter("orderStatusOpen", OrderStatus.OPEN);
		q.setParameter("orderStatusPartial", OrderStatus.PARTIAL);
		q.setMaxResults(1);
		
		if (!q.getResultList().isEmpty())
			tradeOrder = (TradeOrder)q.getResultList().get(0);
		
		Double lowPrice = 0d;
		if (!(tradeOrder == null)){
			lowPrice = tradeOrder.getPrice();
		}
		
		return lowPrice;	
	}



	@Transactional
	public Long getVolume(Symbol symbol) {
		//TradeOrder tradeOrder = null;
		Long totalVolume = 0L;
		Query q = super.entityManager.createQuery(" SELECT COALESCE(SUM(tOrder.quantity), 0) as totalVolume from  TradeOrder tOrder WHERE ((tOrder.symbol = :symbol) AND (DATE(tOrder.created) = CURDATE()) AND (tOrder.orderType = :orderType) AND (tOrder.orderStatus = :orderStatusClosed)) ");
		q.setParameter("symbol", symbol);
		q.setParameter("orderType", OrderType.SELL);
		q.setParameter("orderStatusClosed", OrderStatus.CLOSED);
		
		if (!q.getResultList().isEmpty())
			totalVolume = (Long)q.getResultList().get(0);
		
//		Double lowPrice = 0d;
//		if (!tradeOrder.equals(null)){
//			lowPrice = tradeOrder.getPrice();
//		}
		
		return totalVolume;	
	}


	
	/*******
	 *The average for the symbol for the day 
	 **/
	@Transactional
	public Double getAverage(Symbol symbol) {
		
		Double priceQuantityTotals = 0.0;
		Long totalVolume = 0L;
		//Get the totals - price * quantity
		priceQuantityTotals = getPriceQuantityTotals(symbol);
		
		//Get the volumes - 
		totalVolume = getVolume(symbol);
		
		//Divide totals by volume - this is the average
		if (totalVolume == 0L){
			totalVolume = 1L;
		}
		Double averageForDay = priceQuantityTotals / totalVolume;
		// TODO Auto-generated method stub
		return averageForDay;
	}
	
	@Transactional
	private Double getPriceQuantityTotals(Symbol symbol){
		Double priceQuantityTotals = 0.0;
		Query q = super.entityManager.createQuery(" SELECT COALESCE(SUM(tOrder.quantity * tOrder.price), 0.0) as priceQuantityTotals from  TradeOrder tOrder WHERE ((tOrder.symbol = :symbol) AND (DATE(tOrder.created) = CURDATE()) AND (tOrder.orderType = :orderType) AND (tOrder.orderStatus = :orderStatusClosed)) ");
		q.setParameter("symbol", symbol);
		q.setParameter("orderType", OrderType.SELL);
		q.setParameter("orderStatusClosed", OrderStatus.CLOSED);
		
		if (!q.getResultList().isEmpty())
			priceQuantityTotals = (Double)q.getResultList().get(0);
		
//		Double lowPrice = 0d;
//		if (!tradeOrder.equals(null)){
//			lowPrice = tradeOrder.getPrice();
//		}
		
		return priceQuantityTotals;	
	}
	

}
