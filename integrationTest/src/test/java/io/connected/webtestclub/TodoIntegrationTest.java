package io.connected.webtestclub;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TodoIntegrationTest {

	private HttpCall httpCall;

	@Before
	public void setup(){
		httpCall = new HttpCall();
	}

	@Test
	public void validateEmpty() throws IOException {
		HttpResponse result = httpCall.get("/todo/");
		assertEquals("{\"message\":\"Ok!\",\"body\":[]}", result.getBody());
		assertEquals(200, result.getHttpStatusCode());
	}

}
