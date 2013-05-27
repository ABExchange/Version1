package org.exchangesystem.dto;

import java.io.Serializable;

import org.exchangesystem.model.Symbol;

public class SymbolBalance implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Symbol symbol;
	private Double totalBalance;
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	public Double getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}
	
	

}
