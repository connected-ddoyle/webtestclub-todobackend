package io.connected.webtestclub;

import okhttp3.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpCall {
	public static final String baseUrl = "http://localhost:8080";
	public static final MediaType JSON
			= MediaType.get("application/json; charset=utf-8");

	private final OkHttpClient client;

	public HttpCall(){
		client = new OkHttpClient();
	}

	public HttpResponse get(String path) throws IOException {
		Request request = new Request.Builder()
				.url(getUrl(path))
				.get()
				.build();
		try (Response response = client.newCall(request).execute()) {
			return new HttpResponse(response);
		}
	}

	public HttpResponse delete(String path) throws IOException {
		Request request = new Request.Builder()
				.url(getUrl(path))
				.delete()
				.build();
		try (Response response = client.newCall(request).execute()) {
			return new HttpResponse(response);
		}
	}

	public HttpResponse post(String path, String jsonBody) throws IOException {
		RequestBody body = RequestBody.create(JSON, jsonBody);
		Request request = new Request.Builder()
				.url(getUrl(path))
				.post(body)
				.build();
		try (Response response = client.newCall(request).execute()) {
			return new HttpResponse(response);
		}
	}

	private String getUrl(String path) throws MalformedURLException {
		String url = baseUrl + path;
		new URL(url); //Validate to see if URL is ok.
		return url;
	}
}
