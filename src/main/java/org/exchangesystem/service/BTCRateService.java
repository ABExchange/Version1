package org.exchangesystem.service;

import java.util.List;

import org.exchangesystem.model.BTCRate;
import org.exchangesystem.model.Symbol;

public interface BTCRateService extends BaseService<BTCRate> {
	
	public List<BTCRate> findAll(Symbol symbol);

}
