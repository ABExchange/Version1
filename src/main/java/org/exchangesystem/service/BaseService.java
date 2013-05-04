package org.exchangesystem.service;

import java.util.List;

public interface BaseService<T> {

	public void add(T entity);

	public List<T> findAll();

	public T findById(Long id);

	public void remove(T entity);

	public void update(T entity);

	public Long count();

}
