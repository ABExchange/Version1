package org.exchangesystem.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@DiscriminatorValue(value="bank")
@Table(name="bank")
public class Bank extends DomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountname;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cuntry")
	private Country country;
	
	private String bankName;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="symbol")
	private Symbol symbol;
	
	private String acountNo;
	private String institutionId;
	private String bankTransitNo;
	
	private String branchAddress;
	private String branchPhoneNo;
	private String swiftCode;
	
	//for internation banks
	private String iban;

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public String getAcountNo() {
		return acountNo;
	}

	public void setAcountNo(String acountNo) {
		this.acountNo = acountNo;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getBankTransitNo() {
		return bankTransitNo;
	}

	public void setBankTransitNo(String bankTransitNo) {
		this.bankTransitNo = bankTransitNo;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchPhoneNo() {
		return branchPhoneNo;
	}

	public void setBranchPhoneNo(String branchPhoneNo) {
		this.branchPhoneNo = branchPhoneNo;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public String toString() {
		return accountname + " " + bankName
				+ " " + acountNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((accountname == null) ? 0 : accountname.hashCode());
		result = prime * result
				+ ((acountNo == null) ? 0 : acountNo.hashCode());
		result = prime * result
				+ ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		Bank other = (Bank) obj;
		if (accountname == null) {
			if (other.accountname != null)
				return false;
		} else if (!accountname.equals(other.accountname))
			return false;
		if (acountNo == null) {
			if (other.acountNo != null)
				return false;
		} else if (!acountNo.equals(other.acountNo))
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

}
