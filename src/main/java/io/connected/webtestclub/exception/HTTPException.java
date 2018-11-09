package io.connected.webtestclub.exception;

import io.connected.webtestclub.model.ErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletException;

public class HTTPException extends ServletException {

	private final String message;
	private final HttpStatus code;

	public HTTPException(String message, Throwable nestedException, HttpStatus code) {
		super(message, nestedException);
		this.message = message;
		this.code = code;
	}

	ResponseEntity<ErrorResponseModel> getResponse() {
		return new ResponseEntity<>(new ErrorResponseModel(message, code.value()), code);
	}
}
