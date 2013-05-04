package org.exchangesystem.daoimp;

import org.exchangesystem.dao.DepositDao;
import org.exchangesystem.model.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepositDaoJpaImp extends AbstractJpaDao<Deposit> implements
		DepositDao {

	@Autowired
	ExchangeSystemSession exchangeSystemSession;
	
	public DepositDaoJpaImp(){
		setClazz(Deposit.class);
		setExchangeManagerSession(exchangeSystemSession);
	}
}
