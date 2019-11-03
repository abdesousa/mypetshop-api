package com.mypetshop.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypetshop.api.components.Messages;
import com.mypetshop.api.dto.ApiErrorDTO;
import com.mypetshop.api.dto.CartDTO;
import com.mypetshop.api.dto.ItemDTO;
import com.mypetshop.api.dto.MainDTO;
import com.mypetshop.api.persistence.model.Cart;
import com.mypetshop.api.persistence.model.Item;
import com.mypetshop.api.persistence.model.ItemPK;
import com.mypetshop.api.persistence.model.User;
import com.mypetshop.api.service.CartService;
import com.mypetshop.api.service.ItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

// TODO remove the constants.
@Api(value = "/cart", description = "Operations pertaining to the shopping cart on My PetShop Online Store")
@CrossOrigin()
@RestController
public class CartController {

	private Messages messages;
	private CartService cartService;
	private ItemService itemService;

	@Autowired
	public CartController(CartService cartService, ItemService itemService, Messages messages) {
		this.cartService = cartService;
		this.itemService = itemService;
		this.messages = messages;
	}

	@ApiOperation(value = "View the information of a shopping cart based on the selected user id", response = CartDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 400, message = "011 - There are no shopping cart for this user!"),

	})
	@GetMapping("/cart/{userId}")
	@ResponseBody
	public ResponseEntity<?> getByUserId(@PathVariable("userId") Integer userId) {

		Optional<Cart> cart = cartService.getByUserId(Optional.ofNullable(userId));

		if (cart.isPresent()) {

			List<Item> items = itemService.listByCartId(Optional.of(cart.get().getCartId()));

			CartDTO cartDTO = new CartDTO(cart.get().getCartId(), cart.get().getUser().getUserId());

			cartDTO.setItems(items.stream()
					.map(i -> new ItemDTO(i.getItemPK().getCartId(), i.getItemPK().getProductId(),
							i.getProductItemValue(), i.getProductItemQuantity(), i.getProductItemName(),
							i.getProductItemUrl()))
					.collect(Collectors.toList()));

			return new ResponseEntity<MainDTO>(cartDTO, HttpStatus.OK);

		} else {

			return new ResponseEntity<MainDTO>(new ApiErrorDTO(messages.get("msg.cart.notexists.detail")),
					HttpStatus.BAD_REQUEST);

		}
	}

	@ApiOperation(value = "View all shop carts ordered by total value.", response = CartDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 400, message = "011 - There are no shopping cart for this user!"),

	})
	@GetMapping("/cart")
	@ResponseBody
	public ResponseEntity<?> list() {

		List<Cart> cartList = cartService.list();
		List<CartDTO> cartDTOList = new ArrayList<CartDTO>();

		for (Cart cart : cartList) {

			List<Item> items = itemService.listByCartId(Optional.of(cart.getCartId()));

			CartDTO cartDTO = new CartDTO(cart.getCartId(), cart.getUser().getUserId());

			cartDTO.setItems(items.stream()
					.map(i -> new ItemDTO(i.getItemPK().getCartId(), i.getItemPK().getProductId(),
							i.getProductItemValue(), i.getProductItemQuantity(), i.getProductItemName(),
							i.getProductItemUrl()))
					.collect(Collectors.toList()));

			cartDTO.setTotalValue(cartDTO.getItems().stream().map(s -> s.getProductItemValue()).reduce(BigDecimal.ZERO,
					BigDecimal::add));

			cartDTOList.add(cartDTO);

		}

		return new ResponseEntity<List<MainDTO>>(cartDTOList.stream()
				.sorted((o1, o2) -> o1.getTotalValue().compareTo(o2.getTotalValue())).collect(Collectors.toList()),
				HttpStatus.OK);

	}

	@ApiOperation(value = "Insert an item into shopping cart.", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully inserted a product."),
			@ApiResponse(code = 400, message = "013 - Error while inserting an item into shopping cart"),

	})
	@PostMapping("/cart/item")
	@ResponseBody
	public ResponseEntity<?> insertItem(@RequestBody ItemDTO itemDTO) {

		Optional<Cart> cartExistsByUser = this.cartService.getByUserId(Optional.of(itemDTO.getUserId()));

		if (!cartExistsByUser.isPresent()) {

			Cart cart = new Cart(new User(itemDTO.getUserId()));
			Optional<Cart> cartReturn = this.cartService.save(Optional.of(cart));
			itemDTO.setCartId(cartReturn.get().getCartId());

		} else {
			itemDTO.setCartId(cartExistsByUser.get().getCartId());
		}

		Item item = new Item(new ItemPK(itemDTO.getCartId(), itemDTO.getProductId()), itemDTO.getProductItemValue(),
				itemDTO.getProductItemQuantity(), itemDTO.getProductItemName(), itemDTO.getProductItemUrl());

		Optional<Item> itemReturn = this.itemService.save(Optional.of(item));

		if (itemReturn.isPresent()) {

			return new ResponseEntity<MainDTO>(new ItemDTO(itemReturn.get().getItemPK().getCartId(),
					itemReturn.get().getItemPK().getProductId(), itemDTO.getUserId(),
					itemReturn.get().getProductItemValue(), itemReturn.get().getProductItemQuantity(),
					itemReturn.get().getProductItemName(), itemReturn.get().getProductItemUrl()), HttpStatus.OK);

		} else {

			return new ResponseEntity<MainDTO>(new ApiErrorDTO(messages.get("msg.cart.item.create.detail")),
					HttpStatus.BAD_REQUEST);

		}
	}

	@ApiOperation(value = "Delete an item from shopping cart.", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted a item on the shopping cart."),
			@ApiResponse(code = 500, message = "015 - Error while deleting a user."),

	})
	@DeleteMapping("/cart/item")
	@ResponseBody
	public ResponseEntity<?> removeItem(@RequestBody ItemDTO itemDTO) {

		Item item = new Item(new ItemPK(itemDTO.getCartId(), itemDTO.getProductId()), itemDTO.getProductItemValue(),
				itemDTO.getProductItemQuantity(), itemDTO.getProductItemName(), itemDTO.getProductItemUrl());

		this.itemService.delete(Optional.of(item));

		return new ResponseEntity<MainDTO>(itemDTO, HttpStatus.OK);

	}
}
