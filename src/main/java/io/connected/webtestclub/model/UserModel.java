package io.connected.webtestclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.connected.webtestclub.respository.entity.UserEntity;

import java.sql.Timestamp;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class UserModel {
	protected int id;
	protected Timestamp createdDate;
	protected String email;
	protected String password;
	protected String username;

	protected UserModel() {
	}

	protected UserModel(UserEntity userEntity) {
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

	public static class SimpleUserModel extends UserModel {
		public SimpleUserModel(UserEntity user) {
			super(user);
		}

		public SimpleUserModel() {
		}

		@JsonIgnore
		public int getId() {
			return id;
		}

		@JsonIgnore
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

		@JsonIgnore
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

	public static class DetailedUserModel extends UserModel {
		public DetailedUserModel() {
		}

		public DetailedUserModel(UserEntity userEntity) {
			super(userEntity);
		}

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
}
