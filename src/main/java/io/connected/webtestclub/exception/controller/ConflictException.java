package io.connected.webtestclub.exception.controller;

import io.connected.webtestclub.exception.HTTPException;
import io.connected.webtestclub.exception.service.DuplicateEntryException;
import org.springframework.http.HttpStatus;

public class ConflictException extends HTTPException {
    public ConflictException(DuplicateEntryException e) {
        super("Object already exists", e, HttpStatus.CONFLICT);
    }
}
