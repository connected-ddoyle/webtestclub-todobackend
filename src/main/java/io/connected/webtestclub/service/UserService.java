package io.connected.webtestclub.service;

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
	}

	public void register(UserModel.DetailedUserModel userModel) {
		UserEntity userEntity = userModel.getEntity();
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		usersRepository.save(userEntity);
	}

	public UserModel.SimpleUserModel getUser(String username) {
		return new UserModel.SimpleUserModel(usersRepository.findByUsername(username));
	}
}
