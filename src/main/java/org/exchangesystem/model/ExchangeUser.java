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
}
