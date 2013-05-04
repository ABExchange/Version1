package org.exchangesystem.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@DiscriminatorValue(value="WITHDRAWAL")
@Table(name="withdrawal")
public class Withdrawal extends DomainObject {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "account_id")
		private Account account;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "symbol_id")
		private Symbol symbol;
		
		private Double amount;
		private String bitcoinaddress;
		public Account getAccount() {
			return account;
		}
		public void setAccount(Account account) {
			this.account = account;
		}
		public Symbol getSymbol() {
			return symbol;
		}
		public void setSymbol(Symbol symbol) {
			this.symbol = symbol;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public String getBitcoinaddress() {
			return bitcoinaddress;
		}
		public void setBitcoinaddress(String bitcoinaddress) {
			this.bitcoinaddress = bitcoinaddress;
		}
		@Override
		public String toString() {
			return account + " " + symbol
					+ " " + amount;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result
					+ ((account == null) ? 0 : account.hashCode());
			result = prime * result
					+ ((amount == null) ? 0 : amount.hashCode());
			result = prime * result
					+ ((symbol == null) ? 0 : symbol.hashCode());
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
			Withdrawal other = (Withdrawal) obj;
			if (account == null) {
				if (other.account != null)
					return false;
			} else if (!account.equals(other.account))
				return false;
			if (amount == null) {
				if (other.amount != null)
					return false;
			} else if (!amount.equals(other.amount))
				return false;
			if (symbol == null) {
				if (other.symbol != null)
					return false;
			} else if (!symbol.equals(other.symbol))
				return false;
			return true;
		}
		
		
		
		
}
