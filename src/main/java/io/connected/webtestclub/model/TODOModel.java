package io.connected.webtestclub.model;

import io.connected.webtestclub.respository.entity.TODOEntity;

public class TODOModel {
	public String todo;

	public TODOModel(){};
	public TODOModel(String todo) {
		this.todo = todo;
	}
	public TODOModel(TODOEntity todo) {
		this.todo = todo.getTodo();
	}

	public String getTodo() {
		return todo;
	}

	public TODOEntity toEntity() {
		TODOEntity todoEntity = new TODOEntity();
		todoEntity.setTodo(todo);
		return todoEntity;
	}
}
