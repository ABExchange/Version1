package org.exchangesystem.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "ORDERTRX")
@Table(name = "ordertransaction")
public class OrderTransaction extends DomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tradeorder")
	private TradeOrder tradeOrder;

	private Long quantity;
	private Double price;
	private Double total;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "symbol")
	private Symbol symbol;

	private Double fee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "correspondingorder")
	private TradeOrder correspondingOrder;

	private OrderType orderType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "btcrate")
	private BTCRate btcRate;

	private OrderTransactionType transactionType;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "transaction_orders",
	joinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "tradeorder_id", referencedColumnName = "id")
	)
	private List<TradeOrder> sourceOrderList;

	public BTCRate getBtcRate() {
		return btcRate;
	}

	public void setBtcRate(BTCRate btcRate) {
		this.btcRate = btcRate;
	}

	public TradeOrder getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(TradeOrder tradeOrder) {
		this.tradeOrder = tradeOrder;
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

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public TradeOrder getCorrespondingOrder() {
		return correspondingOrder;
	}

	public void setCorrespondingOrder(TradeOrder correspondingOrder) {
		this.correspondingOrder = correspondingOrder;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public OrderTransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(OrderTransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public List<TradeOrder> getSourceOrderList() {
		return sourceOrderList;
	}

	public void setSourceOrderList(List<TradeOrder> sourceOrderList) {
		this.sourceOrderList = sourceOrderList;
	}

	@Override
	public String toString() {
		return tradeOrder + " quantity = " + quantity + " symbol= " + symbol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((orderType == null) ? 0 : orderType.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result
				+ ((tradeOrder == null) ? 0 : tradeOrder.hashCode());
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
		OrderTransaction other = (OrderTransaction) obj;
		if (orderType != other.orderType)
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
		if (tradeOrder == null) {
			if (other.tradeOrder != null)
				return false;
		} else if (!tradeOrder.equals(other.tradeOrder))
			return false;
		return true;
	}

}
