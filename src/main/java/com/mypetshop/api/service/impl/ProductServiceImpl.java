package com.mypetshop.api.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypetshop.api.persistence.model.Product;
import com.mypetshop.api.persistence.repository.ProductRepository;
import com.mypetshop.api.service.ProductService;


/**
 * Service interface which contains the implementation of the methods to perform business rules for cart.
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
	@Override
	public List<Product> list() {
		
		List<Product> productList = productRepository.findAll();
		return ((productList == null) || (productList.isEmpty())) ? Collections.emptyList():productList;
		
	}

	@Override
	public Optional<Product> getById(Optional<Integer> productId) {

		if (productId.isPresent()) {
			return productRepository.findById(productId.get());
		} else {
			return Optional.empty();
		}	
	}	

	@Override
	public Optional<Product> save(Optional<Product> product) {
		if (product.isPresent()) {
			return Optional.of(productRepository.save(product.get()));
		} else {
			return Optional.empty();
		}			
	}


	@Override
	public void delete(Optional<Product> product) {

		if (product.isPresent()) {
			productRepository.delete(product.get());
		}
		
	}

}
