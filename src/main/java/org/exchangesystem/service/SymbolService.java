package org.exchangesystem.service;

import org.exchangesystem.model.Symbol;

public interface SymbolService extends BaseService<Symbol> {
	public Symbol findSymbol(String code);

	public Double getLastPrice(Symbol symbol);

	public Double getHighPrice(Symbol symbol);

	public Double getLowPrice(Symbol symbol);

	public Long getVolume(Symbol symbol);

	public Double getAverage(Symbol symbol);

}
