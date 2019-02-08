package io.connected.webtestclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.connected.webtestclub.respository.entity.UserEntity;

import java.sql.Timestamp;

@SuppressWarnings({"WeakerAccess", "unused"})
public class UserModel {
	private int id;
	private Timestamp createdDate;
	private String email;
	private String password;
	private String username;

	public UserModel() {
	}

	public UserModel(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.createdDate = userEntity.getCreatedDate();
		this.password = userEntity.getPassword();
		this.username = userEntity.getUsername();
		this.email = userEntity.getEmail();
	}

	@JsonIgnore
	public UserEntity getEntity() {
		UserEntity userEntity = new UserEntity();
		userEntity.setCreatedDate(createdDate);
		userEntity.setEmail(email);
		userEntity.setPassword(password);
		userEntity.setUsername(username);
		userEntity.setId(id);
		return userEntity;
	}

	@JsonIgnore
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
