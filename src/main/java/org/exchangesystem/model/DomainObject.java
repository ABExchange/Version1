package org.exchangesystem.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(
		name="discriminator",
		discriminatorType=DiscriminatorType.STRING
		)
@DiscriminatorValue(value="D")
public class DomainObject implements Serializable{

	private static final long serialVersionUID = -3670524568384167939L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	protected Long id;
	
	private Timestamp created;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="createdby")
	private ExchangeUser createdBy;
	
	private Timestamp updated;
	
	@Column(length=1)
	private String isactive;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="updatedby")
	private ExchangeUser updatedBy;
	
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Timestamp getCreated() {
		return created;
	}



	public void setCreated(Timestamp created) {
		this.created = created;
	}



	public ExchangeUser getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(ExchangeUser createdBy) {
		this.createdBy = createdBy;
	}



	public Timestamp getUpdated() {
		return updated;
	}



	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}



	public String getIsactive() {
		return isactive;
	}



	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}



	public ExchangeUser getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(ExchangeUser updatedBy) {
		this.updatedBy = updatedBy;
	}



	public boolean isNew(){
		return id==null;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainObject other = (DomainObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}