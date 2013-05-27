package org.exchangesystem.dao;

import java.util.List;

import org.exchangesystem.model.ExchangeUser;


public interface ExchangeUserDao extends Dao<ExchangeUser>{

	public ExchangeUser findUser(String email);
	public Long getLastAccountNo();
	public List<ExchangeUser> getAllUsersWithoutAccountNo();



}
