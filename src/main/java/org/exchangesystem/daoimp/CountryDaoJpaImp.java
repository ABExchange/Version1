package org.exchangesystem.daoimp;

import org.exchangesystem.dao.CountryDao;
import org.exchangesystem.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoJpaImp extends AbstractJpaDao<Country> implements
		CountryDao {
	@Autowired
	ExchangeSystemSession exchangeSystemSession;

	public CountryDaoJpaImp(){
		setClazz(Country.class);
		setExchangeManagerSession(exchangeSystemSession);
	}
}
