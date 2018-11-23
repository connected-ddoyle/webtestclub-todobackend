package io.connected.webtestclub.exception.controller;

import io.connected.webtestclub.exception.service.DuplicateEntryException;

public class ConflictException extends Throwable {
    public ConflictException(DuplicateEntryException e) {
        super("Object already exists", e);
    }
}
