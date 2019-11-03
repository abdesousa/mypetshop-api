package com.mypetshop.api.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_USER")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
    @NotBlank
    @NotNull
	@Column(name = "user_nm")
	private String userName;

    
    @NotBlank(message = "{msg.user.email.blank.detail}")
    @NotNull(message = "{msg.user.email.null.detail")
    @Email(message = "{msg.user.email.invalid.detail}")
	@Column(name = "user_email")
	private String userEmail;

	
	public User() {
		super();
	}

	public User(Integer userId, String userName, String userEmail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
	}

	
	public User(Integer userId) {
		this(userId,null, null);		
	}

	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
