package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.AccountDao;
import org.exchangesystem.model.Account;
import org.exchangesystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {
	
	@Autowired
	AccountDao accountDao;

	public void add(Account account) {
		accountDao.save(account);
	}

	public List<Account> findAll() {
		return accountDao.findAll();
	}

	public Account findById(Long id) {
		return accountDao.findOne(id);
	}

	public void remove(Account account) {
		accountDao.delete(account);
	}

	public void update(Account account) {
		accountDao.update(account);
	}

	public Long count() {
		return accountDao.count();
	}
}
