package org.exchangesystem.daoimp;

import org.exchangesystem.dao.TransferMethodDao;
import org.exchangesystem.model.TransferMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransferMethodDaoJpaImp extends AbstractJpaDao<TransferMethod>
		implements TransferMethodDao {

	@Autowired
	ExchangeSystemSession exchangeSystemSession;
	
	public TransferMethodDaoJpaImp(){
		setClazz(TransferMethod.class);
		setExchangeManagerSession(exchangeSystemSession);
	}

}
