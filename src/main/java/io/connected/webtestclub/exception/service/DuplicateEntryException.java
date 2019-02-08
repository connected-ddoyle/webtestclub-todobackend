package io.connected.webtestclub.exception.service;

public class DuplicateEntryException extends Throwable {
	public DuplicateEntryException() {
		super("Duplicate entry found");
	}
}
