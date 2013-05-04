package org.exchangesystem.daoimp;

import org.exchangesystem.dao.WithdrawalDao;
import org.exchangesystem.model.Withdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WithdrawalDaoJpaImp extends AbstractJpaDao<Withdrawal> implements
		WithdrawalDao {

	@Autowired
	ExchangeSystemSession exchangeSystemSession;
	
	public WithdrawalDaoJpaImp(){
		setClazz(Withdrawal.class);
		setExchangeManagerSession(exchangeSystemSession);
	}
	
}
