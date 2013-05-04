package org.exchangesystem.dao;

import java.util.List;

import org.exchangesystem.model.BTCRate;
import org.exchangesystem.model.Symbol;

public interface BTCRateDao extends Dao<BTCRate> {
	
	public List<BTCRate> findAll(Symbol symbol);


}
