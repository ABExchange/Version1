package org.exchangesystem.daoimp;

import javax.persistence.Query;

import org.exchangesystem.dao.SymbolDao;
import org.exchangesystem.model.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SymbolDaoJpaImp extends AbstractJpaDao<Symbol> implements
		SymbolDao {
	
	
	public SymbolDaoJpaImp(){
		setClazz(Symbol.class);
		setExchangeManagerSession(exchangeManagerSession);
		
	}
	
	@Autowired
	ExchangeSystemSession exchangeManagerSession;

	
	
	@Transactional
	public Symbol findSymbol(String code){
		Symbol symbol = null;
		Query q = super.entityManager.createQuery(" SELECT s from  Symbol s WHERE (s.code = :code) ");
		q.setParameter("code", code);
		
		if (!q.getResultList().isEmpty())
			symbol = (Symbol)q.getResultList().get(0);
		
		return symbol;
	}

}
