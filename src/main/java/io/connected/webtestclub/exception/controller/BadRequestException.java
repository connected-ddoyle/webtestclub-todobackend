package io.connected.webtestclub.exception.controller;

import io.connected.webtestclub.exception.HTTPException;
import io.connected.webtestclub.exception.service.InvalidTodoNameException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends HTTPException {
	public BadRequestException(InvalidTodoNameException e) {
		super(e.getMessage(), e, HttpStatus.BAD_REQUEST);
	}
}
