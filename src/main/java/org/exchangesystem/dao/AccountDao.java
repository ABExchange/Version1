package org.exchangesystem.dao;

import java.util.List;

import org.exchangesystem.model.Account;
import org.exchangesystem.model.Deposit;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.Withdrawal;

public interface AccountDao extends Dao<Account> {
	
	public List<Deposit> findAllDeposit(ExchangeUser exchangeUser, Symbol symbol);
	public Double getTotalDeposit(ExchangeUser exchangeUser, Symbol symbol);
	
	public List<Withdrawal> findAllWithdrawals(ExchangeUser exchangeUser, Symbol symbol);
	public Double getTotalWithdrawal(ExchangeUser exchangeUser, Symbol symbol);
	
	public Double getTotalBuyOrders(ExchangeUser exchangeUser, Symbol symbol);
	public Double getTotalSellOrders(ExchangeUser exchangeUser, Symbol symbol);
	
	public Double getBalance(Symbol symbol);
	
	
	

}
