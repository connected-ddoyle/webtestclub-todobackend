package io.connected.webtestclub;

import okhttp3.Response;

@SuppressWarnings("WeakerAccess")
public class HttpResponse {
	private final Response response;
	private String responseBody;

	public HttpResponse(Response response){
		this.response = response;
		try {
			this.responseBody = response.body().string();
		} catch (Exception e) {
			this.responseBody = null;
		}
	}

	public String getBody(){
		return responseBody;
	}

	public int getHttpStatusCode(){
		return response.code();
	}
}
