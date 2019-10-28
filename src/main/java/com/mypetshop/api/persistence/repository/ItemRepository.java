/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypetshop.api.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mypetshop.api.persistence.model.Item;
import com.mypetshop.api.persistence.model.ItemPK;

/**
 * Interface which contains the methods to interact with the Item model.
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
public interface ItemRepository extends JpaRepository<Item, ItemPK> {    
	
    /**
     * List items from cart ID.
     * @param cartId Cart id.
     * @return Optional of Cart object. 
     */
	@Query("select i Item i where i.cartId = :cartId")
    public List<Item> findByCartId(@Param("cartId") Integer cartId);
	
}
