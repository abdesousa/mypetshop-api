package com.mypetshop.api.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypetshop.api.persistence.model.User;
import com.mypetshop.api.persistence.repository.UserRepository;
import com.mypetshop.api.service.UserService;

/**
 * Service interface which contains the implementation of the methods to perform
 * business rules for cart.
 * 
 * @author Alexandre Sousa (abdesousa@gmail.com)
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public List<User> list() {

		List<User> userList = userRepository.findAll();
		return ((userList == null) || (userList.isEmpty())) ? Collections.emptyList() : userList;

	}

	@Override
	public Optional<User> getById(Optional<Integer> userId) {

		if (userId.isPresent()) {
			return userRepository.findById(userId.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<User> save(Optional<User> user) {
		if (user.isPresent()) {
			return Optional.of(this.userRepository.save(user.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void delete(Optional<User> user) {

		if (user.isPresent()) {
			userRepository.delete(user.get());
		}
	}

}
