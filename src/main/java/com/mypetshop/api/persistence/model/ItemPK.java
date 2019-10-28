/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypetshop.api.persistence.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author abdesousa
 */
@Embeddable
public class ItemPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
    @Column(name = "cart_id")
    private Integer cartId;

    @Basic(optional = false)
    @Column(name = "product_id")
    private Integer productId;
    
    public ItemPK() {
    }
    
	public ItemPK(Integer cartId, Integer productId) {
		super();
		this.cartId = cartId;
		this.productId = productId;
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
    
    
}
