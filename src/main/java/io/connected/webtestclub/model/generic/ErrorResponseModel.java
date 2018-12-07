package io.connected.webtestclub.model.generic;

public class ErrorResponseModel {
	public final int code;
	public final String message;

	public ErrorResponseModel(String message, int code) {
		this.message = message;
		this.code = code;
	}
}
