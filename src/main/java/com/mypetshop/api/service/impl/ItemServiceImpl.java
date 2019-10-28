package com.mypetshop.api.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypetshop.api.persistence.model.Item;
import com.mypetshop.api.persistence.repository.ItemRepository;
import com.mypetshop.api.service.ItemService;

/**
 * Service interface which contains the implementation of the methods to perform business rules for cart.
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
@Service
public class ItemServiceImpl implements ItemService{

    private ItemRepository itemRepository;
    
    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
	
	@Override
	public List<Item> listByCartId(Optional<Integer> cartId) {
		if (cartId.isPresent()) {
			List<Item> itemList = itemRepository.findByCartId(cartId.get());
			return (itemList == null) ? Collections.emptyList() : itemList; 
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * In order to become the method thread-safe.
	 */
	@Override
	public synchronized Optional<Item> save(Optional<Item> item) {
		if (item.isPresent()) {
			return Optional.of(itemRepository.save(item.get()));
		} else {
			return Optional.empty();
		}
	}

	/**
	 * In order to become the method thread-safe.
	 */
	@Override
	public synchronized void delete(Optional<Item> item) {
		if (item.isPresent()) {
			itemRepository.delete(item.get());
		}
	}
}
