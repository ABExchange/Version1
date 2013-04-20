package org.exchangesystem.dao;

import java.util.List;


/***
 * @author japheth
 * 
 *Base interface for all the DAO interfaces to inherit from 
 **/
public interface Dao<T> {
	public T findOne(final Long id);
	public List<T> findAll();
	public void save(final T entity);
	public Long saveReturnId(final T entity);
	public T saveReturnEntity(final T entity);
	public void update(final T entity);
	public void delete(final T entity);
	public void deleteById(final Long entityId);
	public Long count();

}
