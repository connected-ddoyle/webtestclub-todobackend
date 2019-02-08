package io.connected.webtestclub.respository.entity;

import javax.persistence.*;

@Entity
@Table(name = "todo")
public class TODOEntity {

	private long id;
	private String todo;

	@Id
	@SequenceGenerator(name = "todoSeq", sequenceName = "todo_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todoSeq")
	@Column(name = "id", nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "todo", nullable = false)
	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}
}
