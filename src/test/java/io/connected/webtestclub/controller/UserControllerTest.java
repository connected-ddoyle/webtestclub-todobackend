package io.connected.webtestclub.controller;

import io.connected.webtestclub.exception.service.DuplicateUserException;
import io.connected.webtestclub.exception.service.InvalidUserNameException;
import io.connected.webtestclub.model.UserModel;
import io.connected.webtestclub.model.generic.ResponseModel;
import io.connected.webtestclub.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class UserControllerTest {
	private UserService userService;

	@Before
	public void setup() {
		userService = mock(UserService.class);
	}

	@Test
	public void testCreateUser_isSuccessful() throws DuplicateUserException, InvalidUserNameException {
		UserController userController = new UserController(userService);
		doNothing().when(userService).register(any(UserModel.DetailedUserModel.class));
		UserModel.DetailedUserModel newUser = new UserModel.DetailedUserModel();
		newUser.setUsername("Kel");
		ResponseEntity<ResponseModel.Simple> response = userController.create(newUser);
		assertEquals("Created", response.getBody().message);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void testCreateUserWithEmoji_isFailed() throws DuplicateUserException, InvalidUserNameException {

	}
}