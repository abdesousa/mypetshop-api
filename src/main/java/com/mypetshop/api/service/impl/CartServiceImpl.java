package com.mypetshop.api.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypetshop.api.persistence.model.Cart;
import com.mypetshop.api.persistence.repository.CartRepository;
import com.mypetshop.api.service.CartService;

/**
 * Service interface which contains the implementation of the methods to perform
 * business rules for cart.
 * 
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	
	
	@Override
	public List<Cart> list() {

		List<Cart> cartList = cartRepository.findAll();
		return ((cartList == null) || (cartList.isEmpty())) ? Collections.emptyList() : cartList;		
		
	}
	
	@Override
	public Optional<Cart> getByUserId(Optional<Integer> userId) {

		if (userId.isPresent()) {
			return cartRepository.findByUserId(userId.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Cart> getById(Optional<Integer> id) {

		if (id.isPresent()) {
			return this.cartRepository.findById(id.get());
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<Cart> save(Optional<Cart> cart) {
		if (cart.isPresent()) {
			return Optional.of(cartRepository.save(cart.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void delete(Optional<Cart> cart) {

		if (cart.isPresent()) {
			cartRepository.delete(cart.get());
		}
		
	}

}
