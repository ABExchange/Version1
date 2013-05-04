package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.BTCRateDao;
import org.exchangesystem.model.BTCRate;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.service.BTCRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BTCRateServiceImp implements BTCRateService {
	
	@Autowired
	BTCRateDao btcRateDao;

	public void add(BTCRate btcRate) {
		btcRateDao.save(btcRate);
	}

	public List<BTCRate> findAll() {
		return btcRateDao.findAll();
	}

	public BTCRate findById(Long id) {
		return btcRateDao.findOne(id);
	}

	public void remove(BTCRate btcRate) {
		btcRateDao.delete(btcRate);
	}
	
	public void update(BTCRate btcRate) {
		btcRateDao.update(btcRate);
	}

	public Long count() {
		return btcRateDao.count();
	}

	public List<BTCRate> findAll(Symbol symbol) {
		return btcRateDao.findAll(symbol);
	}

}
