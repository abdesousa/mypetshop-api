package com.mypetshop.api.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import com.mypetshop.api.persistence.model.User;
import com.mypetshop.api.persistence.repository.UserRepository;
import com.mypetshop.api.service.UserService;

public class UserServiceImplTest {

	private UserRepository userRepository;
	private UserService service;

	public UserServiceImplTest() {

		this.userRepository = Mockito.mock(UserRepository.class);
		this.service = new UserServiceImpl(this.userRepository);

	}

	@Test
	public void givenListWhenHaveUsersThenReturnAllValues() {

		// Given
		List<User> userMockedList = new ArrayList<User>();

		userMockedList.add(new User(1, "User 1", "user1@email.com"));
		userMockedList.add(new User(2, "User 2", "user2@email.com"));

		Mockito.when(userRepository.findAll()).thenReturn(userMockedList);

		List<User> userReturned = this.service.list();

		assertEquals(Integer.valueOf(1), userReturned.get(0).getUserId());
		assertEquals("User 1", userReturned.get(0).getUserName());
		assertEquals("user1@email.com", userReturned.get(0).getUserEmail());

		assertEquals(Integer.valueOf(2), userReturned.get(1).getUserId());
		assertEquals("User 2", userReturned.get(1).getUserName());
		assertEquals("user2@email.com", userReturned.get(1).getUserEmail());

	}

	@Test
	public void givenListWhenEmptyUserTableThenReturnEmptyList() {

		Mockito.when(userRepository.findAll()).thenReturn(Collections.emptyList());
		assertTrue(this.service.list().isEmpty());
	}

	@Test
	public void givenUserIdWhenExistsUserThenReturnUserInformation() {

		Optional<Integer> id = Optional.of(1);
		Optional<User> usermocked = Optional.of(new User(1, "User 1", "user1@email.com"));

		Mockito.when(userRepository.findById(id.get())).thenReturn(usermocked);

		Optional<User> userReturned = this.service.getById(id);


		assertEquals(Integer.valueOf(1), userReturned.get().getUserId());
		assertEquals("User 1", userReturned.get().getUserName());
		assertEquals("user1@email.com", userReturned.get().getUserEmail());

	}

	@Test
	public void givenListWhenEmptyUserTableThenReturnEmpty() {

		Optional<Integer> id = Optional.of(new Integer(1));

		Mockito.when(userRepository.findById(id.get())).thenReturn(Optional.empty());
		assertEquals(Optional.empty(), this.service.getById(id));

	}

}
