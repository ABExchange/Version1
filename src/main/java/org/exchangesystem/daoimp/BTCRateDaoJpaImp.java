package org.exchangesystem.daoimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.exchangesystem.dao.BTCRateDao;
import org.exchangesystem.model.BTCRate;
import org.exchangesystem.model.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BTCRateDaoJpaImp extends AbstractJpaDao<BTCRate> implements
		BTCRateDao {

	@Autowired
	ExchangeSystemSession exchangeSystemSession;

	public BTCRateDaoJpaImp() {
		setClazz(BTCRate.class);
		setExchangeManagerSession(exchangeSystemSession);
	}

	@Transactional
	public List<BTCRate> findAll(Symbol symbol) {
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" from BTCRate btcrate WHERE ((btcrate.symbol = :symbol)) ORDER BY  id DESC ");// .getResultList();
		q.setParameter("symbol", symbol);

		return q.getResultList();
	}

}
