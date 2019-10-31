package com.mypetshop.api.persistence.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ITEM")
public class Item {	

	@EmbeddedId
	private ItemPK itemPK;
		
	@Column(name = "product_item_vl")
	private BigDecimal productItemValue;

	@Column(name = "product_item_url")
	private String productItemUrl;

	@Column(name = "product_item_qty")
	private Integer productItemQuantity;
	
	public Item() {
		super();
	}

	public Item(ItemPK itemPK, BigDecimal productItemValue, Integer productItemQuantity,String productItemUrl) {
		super();
		this.itemPK = itemPK;
		this.productItemValue = productItemValue;
		this.productItemQuantity = productItemQuantity;
		this.productItemUrl = productItemUrl;

	}

	public ItemPK getItemPK() {
		return itemPK;
	}

	public void setItemPK(ItemPK itemPK) {
		this.itemPK = itemPK;
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

	public String getProductItemUrl() {
		return productItemUrl;
	}

	public void setProductItemUrl(String productItemUrl) {
		this.productItemUrl = productItemUrl;
	}
}
