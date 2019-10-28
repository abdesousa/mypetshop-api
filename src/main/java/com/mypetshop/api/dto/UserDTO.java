package com.mypetshop.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;

@ApiModel(description="All information about users from MYPetShop Store.")


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "userId", "userName", "userEmail"})
public class UserDTO extends MainDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	private String userName;

	private String userEmail;

	
	public UserDTO() {
		super();
	}

	public UserDTO(Integer userId, String userName, String userEmail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
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
