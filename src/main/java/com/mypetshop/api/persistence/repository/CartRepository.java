package com.mypetshop.api.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mypetshop.api.persistence.model.Cart;

/**
 * Repository class to the Cart object.
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    /**
     * Find a cart by User ID.
     * @param userId User id.
     * @return Optional of Cart object. 
     */
	@Query("select c from Cart c where c.userId = :userId")
    public Cart findByUserId(@Param("userId") Integer userId);
	
}