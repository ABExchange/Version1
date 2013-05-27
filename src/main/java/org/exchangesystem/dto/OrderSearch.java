package org.exchangesystem.dto;

import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.OrderStatus;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;

public class OrderSearch {
	private OrderType orderType;
	private OrderStatus orderStatus;
	private Symbol symbol;
	private ExchangeUser createdBy;
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	public ExchangeUser getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(ExchangeUser createdBy) {
		this.createdBy = createdBy;
	}
	
	

}
