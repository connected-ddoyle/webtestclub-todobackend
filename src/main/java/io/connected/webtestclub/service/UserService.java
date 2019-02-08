package io.connected.webtestclub.service;

import io.connected.webtestclub.exception.service.DuplicateUserException;
import io.connected.webtestclub.exception.service.InvalidUserNameException;
import io.connected.webtestclub.model.UserModel;
import io.connected.webtestclub.respository.UsersRepository;
import io.connected.webtestclub.respository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
		this.usersRepository = usersRepository;
		this.passwordEncoder = passwordEncoder;
		UserModel admin = new UserModel();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("admin");
		try {
			register(admin);
		} catch (Exception ignore) {}
	}

	public void register(UserModel userModel) throws InvalidUserNameException, DuplicateUserException {
		if (userModel.getUsername() == null || userModel.getUsername().isEmpty()) {
			throw new InvalidUserNameException();
		}

		if (usersRepository.findByUsername(userModel.getUsername()) != null) {
			throw new DuplicateUserException();
		}
		UserEntity userEntity = userModel.getEntity();
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		usersRepository.save(userEntity);
	}

	public UserModel getUser(String username) {
		return new UserModel(usersRepository.findByUsername(username));
	}
}
