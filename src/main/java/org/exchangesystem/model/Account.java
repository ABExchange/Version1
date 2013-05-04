package org.exchangesystem.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@DiscriminatorValue(value="acount")
@Table(name="account")
public class Account extends DomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountNo;
	private Double dailyLimit;
	private Double monthlyLimit;
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public Double getDailyLimit() {
		return dailyLimit;
	}
	public void setDailyLimit(Double dailyLimit) {
		this.dailyLimit = dailyLimit;
	}
	public Double getMonthlyLimit() {
		return monthlyLimit;
	}
	public void setMonthlyLimit(Double monthlyLimit) {
		this.monthlyLimit = monthlyLimit;
	}
	@Override
	public String toString() {
		return accountNo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((accountNo == null) ? 0 : accountNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		return true;
	}
	
	
	
	
	

}
