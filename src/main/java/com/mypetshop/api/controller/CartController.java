package com.mypetshop.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypetshop.api.components.Messages;
import com.mypetshop.api.dto.CartDTO;
import com.mypetshop.api.dto.ErrorDTO;
import com.mypetshop.api.dto.ItemDTO;
import com.mypetshop.api.dto.MainDTO;
import com.mypetshop.api.dto.ProductDTO;
import com.mypetshop.api.persistence.model.Cart;
import com.mypetshop.api.persistence.model.Item;
import com.mypetshop.api.service.CartService;
import com.mypetshop.api.service.ItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="cart", description="Operations pertaining to the shopping cart on My PetShop Online Store")
@CrossOrigin()
@RestController
public class CartController {
	
    private Messages messages;
    private CartService cartService;
    private ItemService itemService;
   
    @Autowired
    public CartController(CartService cartService,ItemService itemService, ModelMapper modelMapper, Messages messages) {
        this.cartService = cartService;
        this.itemService = itemService;
        this.messages = messages;
    }    
    
    @ApiOperation(value = "View the information of a shopping cart", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "001 - Product doesn't exist!"),

    })
    @GetMapping("/card/{userId}")
    @ResponseBody
    public ResponseEntity<?> getByUserId(@PathVariable("userId") Integer userId) {
    	
    	
    	
    	Optional<Cart> cart = cartService.getByUserId(Optional.ofNullable(userId));  	
    	  
    	if (cart.isPresent()) {
    		
    		List<Item> items = itemService.listByCartId(Optional.of(cart.get().getCartId()));
    		
    		CartDTO cartDTO = new CartDTO(cart.get().getCartId(), cart.get().getUser().getUserId());
    		
    		cartDTO.setItems(
				items.stream().map(
					i -> new ItemDTO(
							i.getItemPK().getCartId(),
							i.getItemPK().getProductId(),
							i.getProductItemValue(), 
							i.getProductItemQuantity())
				).collect(Collectors.toList())
			);
    		
    		
    		
    		return new ResponseEntity<MainDTO>(cartDTO, HttpStatus.OK); 
    		
    	} else {
    		
    		return new ResponseEntity<MainDTO>(new ErrorDTO(messages.get("msg.cart.notexists.code"),messages.get("msg.cart.notexists.detail")), HttpStatus.BAD_REQUEST); 
    		
    	}
    }
    
    
}
