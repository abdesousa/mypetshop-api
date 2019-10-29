package com.mypetshop.api.service;

import java.util.Optional;

import com.mypetshop.api.persistence.model.Cart;

/**
 * Service interface which contains the methods to perform business rules for cart.
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
public interface CartService {
	
	public Optional<Cart> getByUserId(Optional<Integer> userId);
	
	public Optional<Cart> getById(Optional<Integer> id);
	
	public Optional<Cart> save(Optional<Cart> cart);
	
	public void delete(Optional<Cart> cart);
	
}
