package org.exchangesystem.daoimp;

import org.exchangesystem.dao.ExchangeUserDao;
import org.exchangesystem.model.ExchangeUser;
import org.springframework.stereotype.Repository;

/****
 *ExchangeUser DAO implementation for all the ExchangeUser management
 * 
 **/
@Repository
public class ExchangeUserDaoJpaImp  extends AbstractJpaDao<ExchangeUser> implements ExchangeUserDao   {
	public ExchangeUserDaoJpaImp(){
		setClazz(ExchangeUser.class);
	}

	public ExchangeUser saveReturnId() {
		return null;
	}
	
	

}
