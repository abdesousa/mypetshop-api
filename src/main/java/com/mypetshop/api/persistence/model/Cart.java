package com.mypetshop.api.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_CART")
public class Cart {	
	
	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;

	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
	private User user;
		
	public Cart() {
		super();
	}

	public Cart(Integer cartId, User user) {
		super();
		this.cartId = cartId;
		this.user = user;
	}

	public Cart(User user) {
		super();
		this.setUser(user);
		this.cartId = 0;
	}
	
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
