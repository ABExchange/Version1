package org.exchangesystem.dao;

import org.exchangesystem.model.Symbol;

public interface SymbolDao extends Dao<Symbol> {
	public Symbol findSymbol(String code);
	
	public Double getLastPrice(Symbol symbol);
	public Double getHighPrice(Symbol symbol);
	public Double getLowPrice(Symbol symbol);
	public Long getVolume(Symbol symbol);
	public Double getAverage(Symbol symbol);
}
