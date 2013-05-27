package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.AccountDao;
import org.exchangesystem.model.Account;
import org.exchangesystem.model.Deposit;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.Withdrawal;
import org.exchangesystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public List<Deposit> findAllDeposit(ExchangeUser exchangeUser, Symbol symbol) {
		return accountDao.findAllDeposit(exchangeUser, symbol);
	}

	@Transactional
	public Double getTotalDeposit(ExchangeUser exchangeUser, Symbol symbol) {
		return accountDao.getTotalDeposit(exchangeUser, symbol);
	}

	@Transactional
	public List<Withdrawal> findAllWithdrawals(ExchangeUser exchangeUser, Symbol symbol) {
		// TODO Auto-generated method stub
		return accountDao.findAllWithdrawals(exchangeUser, symbol);
	}

	@Transactional
	public Double getTotalWithdrawal(ExchangeUser exchangeUser, Symbol symbol) {
		// TODO Auto-generated method stub
		return accountDao.getTotalWithdrawal(exchangeUser, symbol);
	}

	@Transactional
	public Double getTotalBuyOrders(ExchangeUser exchangeUser, Symbol symbol) {
		// TODO Auto-generated method stub
		return accountDao.getTotalBuyOrders(exchangeUser, symbol);
	}

	@Transactional
	public Double getTotalSellOrders(ExchangeUser exchangeUser, Symbol symbol) {
		// TODO Auto-generated method stub
		return accountDao.getTotalSellOrders(exchangeUser, symbol);
	}

	@Transactional
	public Double getBalance(Symbol symbol) {
		return accountDao.getBalance(symbol);
	}
}
