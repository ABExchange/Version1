package org.exchangesystem.daoimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.exchangesystem.dao.AccountDao;
import org.exchangesystem.model.Account;
import org.exchangesystem.model.Deposit;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;
import org.exchangesystem.model.Withdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountDaoJpaImp extends AbstractJpaDao<Account> implements
		AccountDao {
	
	@Autowired
	ExchangeSystemSession exchangeSystemSession;

	public AccountDaoJpaImp(){
		setClazz(Account.class);
		setExchangeManagerSession(exchangeSystemSession);
	}

	@Transactional
	public List<Deposit> findAllDeposit(ExchangeUser exchangeUser, Symbol symbol) {
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" from Deposit deposit where ((deposit.createdBy = :createdBy) AND (deposit.symbol = :symbol)  AND (deposit.isactive = 'Y')) ORDER BY  deposit.id ");// .getResultList();
		q.setParameter("createdBy", exchangeUser);
		q.setParameter("symbol", symbol);
		return q.getResultList();
	}

	@Transactional
	public Double getTotalDeposit(ExchangeUser exchangeUser, Symbol symbol) {
		Double totalamount = 0.0;
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" SELECT COALESCE(SUM(deposit.amount), 0) as totalamount from Deposit deposit WHERE ((deposit.createdBy = :createdBy) AND (deposit.symbol = :symbol)  AND (deposit.isactive = 'Y')) ");// .getResultList();

		q.setParameter("createdBy", exchangeUser);
		q.setParameter("symbol", symbol);
		//TradeOrder tradeOrder;
		if (!q.getResultList().isEmpty())
			totalamount = (Double) q.getResultList().get(0);

		return totalamount;
	}

	@Transactional
	public List<Withdrawal> findAllWithdrawals(ExchangeUser exchangeUser, Symbol symbol) {
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" from Withdrawal withdrawal where ((withdrawal.createdBy = :createdBy) AND (withdrawal.symbol = :symbol)  AND (withdrawal.isactive = 'Y')) ORDER BY  withdrawal.id ");// .getResultList();
		q.setParameter("createdBy", exchangeUser);
		q.setParameter("symbol", symbol);
		
		return q.getResultList();
	}

	@Transactional
	public Double getTotalWithdrawal(ExchangeUser exchangeUser, Symbol symbol) {
		Double totalamount = 0.0;
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" SELECT COALESCE(SUM(withdrawal.amount), 0) as totalamount from Withdrawal withdrawal WHERE ((withdrawal.createdBy = :createdBy) AND (withdrawal.symbol = :symbol)  AND (withdrawal.isactive = 'Y')) ");// .getResultList();
		
		q.setParameter("createdBy", exchangeUser);
		q.setParameter("symbol", symbol);
		//TradeOrder tradeOrder;
		if (!q.getResultList().isEmpty())
			totalamount = (Double) q.getResultList().get(0);

		return totalamount;
	}

	@Transactional
	public Double getTotalBuyOrders(ExchangeUser exchangeUser, Symbol symbol) {
		Double totalamount = 0.0;
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" SELECT COALESCE(SUM(tradeOrder.total), 0) as totalOrder from TradeOrder tradeOrder WHERE ((tradeOrder.createdBy = :createdBy) AND (tradeOrder.symbol = :symbol) AND (tradeOrder.orderType = :orderType) AND (tradeOrder.isactive = 'Y')) ");// .getResultList();

		q.setParameter("createdBy", exchangeUser);
		q.setParameter("symbol", symbol);
		q.setParameter("orderType", OrderType.BUY);
		
		
		
		if (!q.getResultList().isEmpty())
			totalamount = (Double) q.getResultList().get(0);

		return totalamount;
	}

	@Transactional
	public Double getTotalSellOrders(ExchangeUser exchangeUser, Symbol symbol) {
		Double totalamount = 0.0;
		EntityManager em = super.entityManager;
		Query q = em
				.createQuery(" SELECT COALESCE(SUM(tradeOrder.total), 0) as totalOrder from TradeOrder tradeOrder WHERE ((tradeOrder.createdBy = :createdBy) AND (tradeOrder.symbol = :symbol) AND (tradeOrder.orderType = :orderType) AND (tradeOrder.isactive = 'Y')) ");// .getResultList();

		q.setParameter("createdBy", exchangeUser);
		q.setParameter("symbol", symbol);
		q.setParameter("orderType", OrderType.SELL);
		
		
		
		if (!q.getResultList().isEmpty())
			totalamount = (Double) q.getResultList().get(0);

		return totalamount;
	}
	
	@Transactional
	public Double getBalance(Symbol symbol) {

		// Get Total Deposits
		Double totalDeposit = getTotalDeposit(
				exchangeSystemSession.getUser(), symbol);

		// Get Total Sells
		Double totalSell = getTotalSellOrders(
				exchangeSystemSession.getUser(), symbol);
		// Get Total Buys
		Double totalBuy = getTotalBuyOrders(
				exchangeSystemSession.getUser(), symbol);
		// Get Total Withdrawals
		Double totalWithrawal = getTotalWithdrawal(
				exchangeSystemSession.getUser(), symbol);

		Double balance = ((totalDeposit + totalSell) - (totalBuy + totalWithrawal));

		return balance;
	}
	
	
}
