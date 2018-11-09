package io.connected.webtestclub.exception.controller;

import io.connected.webtestclub.exception.HTTPException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends HTTPException {
	public NotFoundException(Throwable nestedException) {
		super(nestedException.getMessage(), nestedException, HttpStatus.NOT_FOUND);
	}
}
