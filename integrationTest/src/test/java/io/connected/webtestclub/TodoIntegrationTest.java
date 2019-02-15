package io.connected.webtestclub;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TodoIntegrationTest {

	private HttpCall httpCall;

	@Before
	public void setup(){
		httpCall = new HttpCall();
	}

	@Test
	public void A_validateEmpty() throws IOException {
		HttpResponse result = httpCall.get("/todo/");
		assertEquals("{\"message\":\"Ok!\",\"body\":[]}", result.getBody());
		assertEquals(200, result.getHttpStatusCode());
	}

	@Test
	public void B_create() throws IOException {
		HttpResponse result = httpCall.post("/todo/", "{\"todo\":\"Hire a PO!\"}");
		assertEquals("{\"message\":\"Created!\"}", result.getBody());
		assertEquals(201, result.getHttpStatusCode());
	}

	//TODO Validate get now has the new item we've inserted.

	//TODO Validate getting a single item through id.

	//TODO Delete an item.

}
