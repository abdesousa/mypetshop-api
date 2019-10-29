package com.mypetshop.api.service.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import com.mypetshop.api.persistence.model.Cart;
import com.mypetshop.api.persistence.model.Product;
import com.mypetshop.api.persistence.model.User;
import com.mypetshop.api.persistence.repository.CartRepository;
import com.mypetshop.api.service.CartService;

public class CartServiceImplTest {

 	private  CartRepository cartRepository;
 	private  CartService service;

    public CartServiceImplTest() {

        this.cartRepository = Mockito.mock(CartRepository.class);	        
        this.service = new CartServiceImpl(this.cartRepository);

    }
 
    @Test
    public void givenUserIdWhenExistsCartThenReturnCartInformation() {

    	Optional<Integer> id = Optional.of(1);
        Optional<Cart> cartmocked = Optional.of(new Cart(1, new User(1, "User 1", "user1@test.com")));          
        
        Mockito.when(cartRepository.findById(id.get())).thenReturn(cartmocked);

        Optional<Cart> cartReturned = this.service.getById(id);

        assertEquals(Integer.valueOf(1), cartReturned.get().getCartId());
        assertEquals("User 1", cartReturned.get().getUser().getUserName());
        assertEquals("user1@test.com", cartReturned.get().getUser().getUserEmail());
        
    }

    @Test
    public void givenListWhenEmptyProductTableThenReturnEmpty() {

    	Optional<Integer> id = Optional.of(new Integer(1));

        Mockito.when(cartRepository.findById(id.get())).thenReturn(Optional.empty());        
        assertEquals(Optional.empty(), this.service.getById(id));
        
    }    

}
