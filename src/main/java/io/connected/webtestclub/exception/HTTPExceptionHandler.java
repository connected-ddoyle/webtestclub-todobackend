package io.connected.webtestclub.exception;

import io.connected.webtestclub.model.ErrorResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HTTPExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(HTTPExceptionHandler.class);

	@ExceptionHandler(HTTPException.class)
	public final ResponseEntity<ErrorResponseModel> handleHTTPException(HTTPException ex) {
		logger.error("Exception happened when processing request.", ex);
		return ex.getResponse();
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponseModel> handleAnyException(Exception e) {
		logger.error("Internal error happened when processing request.", e);
		return new ResponseEntity<>(new ErrorResponseModel("Internal server error", 500), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
