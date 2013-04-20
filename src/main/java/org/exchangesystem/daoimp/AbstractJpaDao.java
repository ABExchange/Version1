package org.exchangesystem.daoimp;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.exchangesystem.model.DomainObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractJpaDao <T extends DomainObject> {
	private Class<T> clazz;
	
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	ExchangeSystemSession exchangeManagerSession;
	
	
	
	public void setProjectManagerSession(ExchangeSystemSession exchangeManagerSession){
		this.exchangeManagerSession = exchangeManagerSession;
	}
	
	
	public void setClazz(final Class<T> classToSet){
		this.clazz = classToSet;
	}

	@Transactional
	public T findOne(final Long id){
		
		T entity = entityManager.find(clazz, id);
		entityManager.flush();
		entityManager.refresh(entity);
		return entity;//entityManager.find(clazz, id);
	}
	
	@Transactional
	public List<T> findAll(){
		return entityManager.createQuery(" from "+clazz.getName()+" ORDER BY id DESC ").getResultList();
	}
	
	
	@Transactional
	public void save(final T entity){
		//entityManager.persist(entity);
		entity.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		entity.setIsactive("Y");
		entity.setCreatedBy(exchangeManagerSession.getUser());
		//entity.setCreatedBy(ProjectManagerSession.getUser());
		entityManager.merge(entity);
		entityManager.flush();
	}
	
	//@Transactional
	public Long saveReturnId(final T entity){
		//entityManager.persist(entity);
		entity.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		entity.setIsactive("Y");
		entity.setCreatedBy(exchangeManagerSession.getUser());
		//entity.setCreatedBy(ProjectManagerSession.getUser());
		
		entityManager.persist(entity);
		entityManager.flush();
//		entityManager.getTransaction().commit();
		//entityManager.
		return entity.getId();
	}
	
	public T saveReturnEntity(final T entity){
		//entityManager.persist(entity);
		entity.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		entity.setIsactive("Y");
		entity.setCreatedBy(exchangeManagerSession.getUser());
		//entity.setCreatedBy(ProjectManagerSession.getUser());
		
		entityManager.persist(entity);
		entityManager.flush();
//		entityManager.getTransaction().commit();
		//entityManager.
		return entity;
	}
	
	@Transactional
	public void update(final T entity){
		entity.setUpdated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		entity.setUpdatedBy(exchangeManagerSession.getUser());
		//entity.setUpdatedBy(ProjectManagerSession.getUser());
		entityManager.merge(entity);
	}
	
	
	
	@Transactional
	public void delete(final T entity){
		entityManager.remove(entity);
	}
	
	@Transactional
	public void deleteById(final Long entityId){
		final T entity = findOne(entityId);
		delete(entity);
	}
	
	@Transactional
	public Long count(){
		//return entityManager.
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(clazz)));
		//cq.where(/*your stuff*/);
		return entityManager.createQuery(cq).getSingleResult();
	}	
}