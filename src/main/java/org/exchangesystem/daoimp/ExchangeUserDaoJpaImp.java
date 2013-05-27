package org.exchangesystem.daoimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.exchangesystem.dao.ExchangeUserDao;
import org.exchangesystem.model.ExchangeUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/****
 * ExchangeUser DAO implementation for all the ExchangeUser management
 * 
 **/
@Repository
public class ExchangeUserDaoJpaImp extends AbstractJpaDao<ExchangeUser>
		implements ExchangeUserDao {
	public ExchangeUserDaoJpaImp() {
		setClazz(ExchangeUser.class);
	}

	public ExchangeUser saveReturnId() {
		return null;
	}

	@Transactional
	public ExchangeUser findUser(String email) {
		ExchangeUser user = null;
		Query q = super.entityManager
				.createQuery(" SELECT eu from  ExchangeUser eu WHERE (eu.email = :userEmail) ");
		q.setParameter("userEmail", email);

		if (!q.getResultList().isEmpty())
			user = (ExchangeUser) q.getResultList().get(0);

		return user;
	}

	@Transactional
	public Long getLastAccountNo() {
		Long totalcount = 0L;
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" SELECT COALESCE(COUNT(eu), 0) as totalcount from ExchangeUser eu WHERE (eu.accountSequence IS NOT NULL) ");// .getResultList();

		if (!q.getResultList().isEmpty())
			totalcount = (Long) q.getResultList().get(0);

		return totalcount;
	}

	@Transactional
	public List<ExchangeUser> getAllUsersWithoutAccountNo() {
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" from ExchangeUser eu where eu.accountSequence IS NULL ORDER BY  eu.id ");// .getResultList();
		return q.getResultList();
	}

}
