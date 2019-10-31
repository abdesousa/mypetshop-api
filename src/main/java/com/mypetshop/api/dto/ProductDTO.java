package com.mypetshop.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;

@ApiModel(description="All information about products from MYPetShop Store.")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productId", "productName", "productValue"})
public class ProductDTO extends MainDTO  implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer productId;

	private String productName;	

	private BigDecimal productValue;
	
	private String productUrl;

	
	public ProductDTO() {
		super();
	}
	

	public ProductDTO(Integer productId, String productName, BigDecimal productValue, String productUrl) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productUrl = productUrl;
		this.productValue = productValue;
	}


	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductValue() {
		return productValue;
	}

	public void setProductValue(BigDecimal productValue) {
		this.productValue = productValue;
	}


	public String getProductUrl() {
		return productUrl;
	}


	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

}
