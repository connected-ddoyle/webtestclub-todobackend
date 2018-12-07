package io.connected.webtestclub.model;

@SuppressWarnings("WeakerAccess")
public class ResponseModel<T> {
	public final String message;
	public final T body;

	public ResponseModel(String message, T body) {
		this.message = message;
		this.body = body;
	}


	public static class Simple{
		public final String message;

		public Simple(String message) {
			this.message = message;
		}
	}
}
