package org.exchangesystem.daoimp;

import org.exchangesystem.dao.BankDao;
import org.exchangesystem.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankDaoJpaImp extends AbstractJpaDao<Bank> implements BankDao {
	
		@Autowired
		ExchangeSystemSession exchangeSystemSession;

		public BankDaoJpaImp(){
			setClazz(Bank.class);
			setExchangeManagerSession(exchangeSystemSession);
		}
}
