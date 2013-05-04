package org.exchangesystem.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value="USERS")
@Table(name="users")
public class ExchangeUser extends DomainObject {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	@NotNull
//	@Size(min=1, max=20)
	@Column(name="first_name")
	private String firstName;
	
//	@NotNull
//	@Size(min=1, max=20)
	@Column(name="last_name")
	private String lastName;
	
//	@NotNull
//	@Size(min=6, max=50)
	private String email;
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@Column(name="join_date")
	private Timestamp joinDate;
	
	@Column(name="profile_image")
	private String profileImage;
	
	@Column(name="profile_image_small")
	private String profileImageSmall;
	
	
	@Column(name="profilephotoid")
	private Long profilePhotoId;
	
	@Transient
	private Boolean isLoggedIn = false;
	
	@Transient
	private String sessionId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Timestamp getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImageSmall() {
		return profileImageSmall;
	}

	public void setProfileImageSmall(String profileImageSmall) {
		this.profileImageSmall = profileImageSmall;
	}

	public Long getProfilePhotoId() {
		return profilePhotoId;
	}

	public void setProfilePhotoId(Long profilePhotoId) {
		this.profilePhotoId = profilePhotoId;
	}

	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return  firstName + " "
				+ lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
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
		ExchangeUser other = (ExchangeUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	
	
	
}
