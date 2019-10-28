package com.mypetshop.api.service;

import java.util.List;
import java.util.Optional;

import com.mypetshop.api.persistence.model.Item;

/**
 * Service interface which contains the methods to perform business rules to the Cart Items.
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
public interface ItemService {

	public List<Item> listByCartId(Optional<Integer> cartId);
	
	public Optional<Item> save(Optional<Item> item);
	
	public void delete(Optional<Item> item);
	
}
