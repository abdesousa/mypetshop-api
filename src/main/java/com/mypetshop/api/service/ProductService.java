package com.mypetshop.api.service;

import java.util.List;
import java.util.Optional;

import com.mypetshop.api.persistence.model.Product;

/**
 * Service interface which contains the methods to perform business rules to the Products of the My Pet Shop.
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
public interface ProductService {

	public List<Product> list();
	
	public Optional<Product> getById(Optional<Integer> productId);
	
	public Optional<Product> save(Optional<Product> product);
		
	public void delete(Optional<Product> product);
}
