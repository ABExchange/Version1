package org.exchangesystem.dto;

import java.sql.Timestamp;

import org.exchangesystem.model.Symbol;

public class AccountHistory {
	private String type;
	private String method;
	private Double amount;
	private Symbol symbol;
	private String status;
	private Timestamp initiated;
	private Double fee;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getInitiated() {
		return initiated;
	}
	public void setInitiated(Timestamp initiated) {
		this.initiated = initiated;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}

}
