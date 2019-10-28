package com.mypetshop.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;

@ApiModel(description="All information about shopping cart.")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "userId", "userName", "userEmail"})
public class CartDTO extends MainDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer cartId;
	
	private Integer userId;

	private List<ItemDTO> items;

	public CartDTO() {
		super();
	}
	
	public CartDTO(Integer cartId, Integer userId) {
		super();
		this.cartId = cartId;
		this.userId = userId;
	}

	public Integer getCartId() {
		return cartId;
	}


	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public List<ItemDTO> getItems() {
		return items;
	}


	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}

	
	
}
