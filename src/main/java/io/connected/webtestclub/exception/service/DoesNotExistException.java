package io.connected.webtestclub.exception.service;

public class DoesNotExistException extends Exception {
	public DoesNotExistException() {
		super("Non existing todo id");
	}
}
