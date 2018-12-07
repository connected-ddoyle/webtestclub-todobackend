package io.connected.webtestclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.connected.webtestclub.respository.entity.TODOEntity;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class TODOModel {
	protected long id;
	protected String todo;

	protected TODOModel(){};
	protected TODOModel(String todo) {
		this.todo = todo;
	}
	protected TODOModel(TODOEntity todo) {
		this.todo = todo.getTodo();
	}

	public String getTodo() {
		return todo;
	}

	public static class DetailedTODOModel extends TODOModel{
		public DetailedTODOModel(TODOEntity entity){
			super(entity);
		}
		public DetailedTODOModel(){}
		public String getTodo() {
			return todo;
		}
		public long getId() {
			return id;
		}
	}

	public static class SimpleTODOModel extends TODOModel{
		public SimpleTODOModel(TODOEntity todoEntity) {
			super(todoEntity);
		}

		public SimpleTODOModel() {}

		public String getTodo() {
			return todo;
		}

		public void setTodo(String todo) {
			this.todo = todo;
		}
	}

	@JsonIgnore
	public TODOEntity toEntity() {
		TODOEntity todoEntity = new TODOEntity();
		todoEntity.setTodo(todo);
		return todoEntity;
	}
}
