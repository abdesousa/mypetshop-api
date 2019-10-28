package com.mypetshop.api.service;

import java.util.List;
import java.util.Optional;

import com.mypetshop.api.persistence.model.User;

/**
 * Service interface which contains the methods to perform business rules to the Users from My Pet Shop.
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
public interface UserService {

	public List<User> list();	
	
	public Optional<User> getById(Optional<Integer> userId);

	public Optional<User> save(Optional<User> user);

	public void delete(Optional<User> user);
	
}
