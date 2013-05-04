package org.exchangesystem.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@DiscriminatorValue(value="SIGNLOG")
@Table(name="signinlog")
public class SignInLog extends DomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String names;

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}
	
	

}
