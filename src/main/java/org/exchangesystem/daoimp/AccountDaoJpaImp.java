package org.exchangesystem.daoimp;

import org.exchangesystem.dao.AccountDao;
import org.exchangesystem.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoJpaImp extends AbstractJpaDao<Account> implements
		AccountDao {
	
	@Autowired
	ExchangeSystemSession exchangeSystemSession;

	public AccountDaoJpaImp(){
		setClazz(Account.class);
		setExchangeManagerSession(exchangeSystemSession);
	}
	
	
}
