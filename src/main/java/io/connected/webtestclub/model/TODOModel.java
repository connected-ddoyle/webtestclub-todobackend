package io.connected.webtestclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.connected.webtestclub.respository.entity.TODOEntity;

@SuppressWarnings({"WeakerAccess", "unused"})
public class TODOModel {
	private long id;
	private String todo;

	public TODOModel(){};
	public TODOModel(String todo) {
		this.todo = todo;
	}
	public TODOModel(TODOEntity todo) {
		this.id = todo.getId();
		this.todo = todo.getTodo();
	}

	public long getId(){
		return id;
	}

	public String getTodo() {
		return todo;
	}

	@JsonIgnore
	public TODOEntity toEntity() {
		TODOEntity todoEntity = new TODOEntity();
		todoEntity.setTodo(todo);
		return todoEntity;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}
}
