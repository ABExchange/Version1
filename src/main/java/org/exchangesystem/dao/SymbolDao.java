package org.exchangesystem.dao;

import org.exchangesystem.model.Symbol;

public interface SymbolDao extends Dao<Symbol> {
	public Symbol findSymbol(String code);

}
