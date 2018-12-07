package io.connected.webtestclub.model;

import io.connected.webtestclub.respository.entity.TODOEntity;

public class DetailedTODOModel {
	public long id;
	public String todo;

	public DetailedTODOModel(){};
	public DetailedTODOModel(String todo) {
		this.todo = todo;
	}
	public DetailedTODOModel(TODOEntity todo) {
		this.todo = todo.getTodo();
		this.id = todo.getId();
	}

	public String getTodo() {
		return todo;
	}
}
