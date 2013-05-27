package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.ExchangeUserDao;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.service.ExchangeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/****
 * @author japheth
 * Purpose:  User persistence service - JPA implmentation 
 **/
@Service
public class ExchangeUserServiceImp implements ExchangeUserService {

	@Autowired
	ExchangeUserDao userDao;
	
	@Transactional
	public void add(ExchangeUser exchangeUser) {
		userDao.save(exchangeUser);
	}

	@Transactional
	public List<ExchangeUser> findAll() {
		return userDao.findAll();
	}

	@Transactional
	public ExchangeUser findById(Long id) {
		return userDao.findOne(id);
	}

	@Transactional
	public void remove(ExchangeUser exchangeUser) {
		userDao.delete(exchangeUser);
	}

	@Transactional
	public void update(ExchangeUser exchangeUser) {
		userDao.update(exchangeUser);
	}

	@Transactional
	public ExchangeUser saveReturnEntity(ExchangeUser exchangeUser) {
		return userDao.saveReturnEntity(exchangeUser);
	}

	@Transactional
	public ExchangeUser findUser(String email) {
		return userDao.findUser(email);
	}

	@Transactional
	public Long getLastAccountNo() {
		return userDao.getLastAccountNo();
	}

	@Transactional
	public List<ExchangeUser> getAllUsersWithoutAccountNo() {
		return userDao.getAllUsersWithoutAccountNo();
	}

}
