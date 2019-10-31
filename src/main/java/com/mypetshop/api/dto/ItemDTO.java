package com.mypetshop.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;

@ApiModel(description="All information about the items of a shopping cart.")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cartId", "userId", "productItemValue", "productItemQuantity"})
public class ItemDTO extends MainDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer cartId;
	
	private Integer productId;
	
	private Integer userId;

	private BigDecimal productItemValue;

	private String productItemUrl;

	private Integer productItemQuantity;

	public ItemDTO() {
		super();
	}

	public ItemDTO(Integer cartId, Integer productId, Integer userId, BigDecimal productItemValue, Integer productItemQuantity, String productItemUrl) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.productItemUrl = productItemUrl;

		this.productItemValue = productItemValue;
		this.productItemQuantity = productItemQuantity;
		this.userId = userId;
	}

	public ItemDTO(Integer cartId, Integer productId, BigDecimal productItemValue, Integer productItemQuantity, String productItemUrl) {
		this(cartId, productId, 0, productItemValue, productItemQuantity,productItemUrl);
	}
	
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public BigDecimal getProductItemValue() {
		return productItemValue;
	}

	public void setProductItemValue(BigDecimal productItemValue) {
		this.productItemValue = productItemValue;
	}

	public Integer getProductItemQuantity() {
		return productItemQuantity;
	}

	public void setProductItemQuantity(Integer productItemQuantity) {
		this.productItemQuantity = productItemQuantity;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProductItemUrl() {
		return productItemUrl;
	}

	public void setProductItemUrl(String productItemUrl) {
		this.productItemUrl = productItemUrl;
	}
	
}
