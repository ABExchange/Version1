package org.exchangesystem.service;

import java.util.List;

import org.exchangesystem.model.ExchangeUser;

public interface ExchangeUserService {
	public void add(ExchangeUser exchangeUser);

	public List<ExchangeUser> findAll();

	public ExchangeUser findById(Long id);

	public void remove(ExchangeUser exchangeU);

	public void update(ExchangeUser exchangeU);
	
	public ExchangeUser saveReturnEntity(final ExchangeUser exchangeUser);
	public ExchangeUser findUser(String email);
	
	public Long getLastAccountNo();
	public List<ExchangeUser> getAllUsersWithoutAccountNo();



}
