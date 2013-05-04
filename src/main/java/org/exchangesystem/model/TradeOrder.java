package org.exchangesystem.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value="TORDER")
@Table(name="tradeorder")
public class TradeOrder extends DomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="symbol_id")
	private Symbol symbol;
	
	private Long quantity;
	private Double price;
	private Double total;
	private Double fee;
	private Double feerate;
	
	private OrderType orderType;
	private OrderStatus orderStatus;
	
	private Long unfulfilledquantity;
	private Long partialquantity;
	
	
	
	public Long getPartialquantity() {
		return partialquantity;
	}
	public void setPartialquantity(Long partialquantity) {
		this.partialquantity = partialquantity;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getFeerate() {
		return feerate;
	}
	public void setFeerate(Double feerate) {
		this.feerate = feerate;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
	
	
	public Long getUnfulfilledquantity() {
		return unfulfilledquantity;
	}
	public void setUnfulfilledquantity(Long unfulfilledquantity) {
		this.unfulfilledquantity = unfulfilledquantity;
	}
	@Override
	public String toString() {
		return symbol + " " + quantity
				+ " " + getCreated();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeOrder other = (TradeOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
	
	
	
	

}
