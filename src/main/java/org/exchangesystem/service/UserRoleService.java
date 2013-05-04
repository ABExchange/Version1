package org.exchangesystem.service;

import java.util.List;

import org.exchangesystem.model.UserRole;

public interface UserRoleService {
	public void add(UserRole userRole);

	public List<UserRole> findAll();

	public UserRole findById(Long id);

	public void remove(UserRole userRole);

	public void update(UserRole userRole);
	public UserRole saveReturnEntity(final UserRole exchangeUser);


}
