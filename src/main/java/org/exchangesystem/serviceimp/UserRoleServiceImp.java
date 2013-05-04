package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.UserRoleDao;
import org.exchangesystem.model.UserRole;
import org.exchangesystem.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserRoleServiceImp implements UserRoleService {
	
	
	UserRoleDao userRoleDao;

	@Transactional
	public void add(UserRole userRole) {
		userRoleDao.save(userRole);
	}

	@Transactional
	public List<UserRole> findAll() {
		return userRoleDao.findAll();
	}

	@Transactional
	public UserRole findById(Long id) {
		return userRoleDao.findOne(id);
	}

	@Transactional
	public void remove(UserRole userRole) {
		userRoleDao.delete(userRole);
	}

	@Transactional
	public void update(UserRole userRole) {
		userRoleDao.update(userRole);
	}

	@Transactional
	public UserRole saveReturnEntity(UserRole exchangeUser) {
		return userRoleDao.saveReturnEntity(exchangeUser);
	}

}
