package org.exchangesystem.daoimp;

import org.exchangesystem.dao.SignInLogDao;
import org.exchangesystem.model.SignInLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInLogDaoJpaImp extends AbstractJpaDao<SignInLog> implements
		SignInLogDao {
	@Autowired
	ExchangeSystemSession exchangeSystemSession;
	
	public SignInLogDaoJpaImp(){
		setClazz(SignInLog.class);
		setExchangeManagerSession(exchangeSystemSession);
	}

}
