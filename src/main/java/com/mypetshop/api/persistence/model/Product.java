package com.mypetshop.api.persistence.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PRODUCT")
public class Product {

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	@Column(name = "product_nm")
	private String productName;

	@Column(name = "product_vl")
	private BigDecimal productValue;
	

	@Column(name = "product_url")
	private String productUrl;
	
	public Product() {
		super();
	}

	
	public Product(Integer productId) {
		this(productId,null,null,null);
	}

	public Product(Integer productId, String productName, BigDecimal productValue, String productUrl) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productValue = productValue;
		this.productUrl = productUrl;

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
