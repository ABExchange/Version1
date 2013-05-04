package org.exchangesystem.service;

import org.exchangesystem.model.Symbol;

public interface SymbolService extends BaseService<Symbol>{
	public Symbol findSymbol(String code);

}
