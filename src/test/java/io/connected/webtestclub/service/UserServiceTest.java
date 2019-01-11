package io.connected.webtestclub.service;

import io.connected.webtestclub.exception.service.InvalidUserNameException;
import io.connected.webtestclub.model.UserModel;
import io.connected.webtestclub.respository.UsersRepository;
import io.connected.webtestclub.respository.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {

	private UserService service;
	private UsersRepository usersRepository;
	private PasswordEncoder passwordEncoder;

	@Before
	public void setup(){
		usersRepository = mock(UsersRepository.class);
		passwordEncoder = mock(PasswordEncoder.class);
		service = new UserService(usersRepository, passwordEncoder);
	}

	@Test(expected = InvalidUserNameException.class)
	public void registerUserWithNullNameFails() throws InvalidUserNameException {

		UserModel.DetailedUserModel userObject = new UserModel.DetailedUserModel();
		userObject.setUsername(null);

		service.register(userObject);

	}

	@Test(expected = InvalidUserNameException.class)
	public void registerUserWithEmptyNameFails() throws InvalidUserNameException {

		UserModel.DetailedUserModel userObject = new UserModel.DetailedUserModel();
		userObject.setUsername("");

		service.register(userObject);

	}

	@Test
	public void registerValidUserCallsSave() throws InvalidUserNameException {

		UserModel.DetailedUserModel userObject = new UserModel.DetailedUserModel();
		userObject.setUsername("mockUserName");

		doReturn(null).when(passwordEncoder).encode(anyString());
		doReturn(null).when(usersRepository).save(any());

		UserEntity userEntity = userObject.getEntity();
		userEntity.setPassword(null);

		service.register(userObject);

		verify(usersRepository).save(userEntity);
	}
}