package io.connected.webtestclub.exception.service;

public class InvalidTodoNameException extends Exception {
	public InvalidTodoNameException() {
		super("Invalid todo name");
	}
}
