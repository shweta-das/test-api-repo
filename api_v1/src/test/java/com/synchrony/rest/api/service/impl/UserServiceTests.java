package com.synchrony.rest.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.synchrony.rest.api.exception.UnexpectedException;
import com.synchrony.rest.api.model.User;
import com.synchrony.rest.api.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	private User user;

	@BeforeEach
	public void setup() {
		// userRepository = Mockito.mock(UserRepository.class);
		// userService = new UserServiceImpl(userRepository);
		user = new User(1L, "admin", "admin123");

	}

	@DisplayName("JUnit test for registerUser method")
	@Test
	public void givenUserObject_whenRegisterUser_thenReturnUserObject() {

		when(userRepository.save(user)).thenReturn(user);
		System.out.println(userRepository);
		System.out.println(userService);

		User savedUser = userService.registerUser(user);

		System.out.println(savedUser);
		assertNotNull(savedUser);
        assertEquals("admin", savedUser.getUsername());
	}

	@DisplayName("JUnit test for null object in registerUser method")
	@Test
	public void givenUserNull_whenRegisterUser_thenReturnExceptiont() {
		assertThrows(UnexpectedException.class, () -> userService.registerUser(null));
	}

	@Test
	public void testRegisterUserDuplicate() {

	}

	@DisplayName("JUnit test for duplicate entry registerUser method")
	@Test
	public void givenUserDuplicate_whenRegisterUser_thenReturnExceptiont() {

	}

	@DisplayName("JUnit test for getAllEmployees method")
	@Test
	public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() {

	}

	@DisplayName("JUnit test for getAllEmployees method with 0 list ")
	@Test
	public void givenEmployeesList_whenGetNoEmployees_thenReturnEmployeesList() {

	}
}