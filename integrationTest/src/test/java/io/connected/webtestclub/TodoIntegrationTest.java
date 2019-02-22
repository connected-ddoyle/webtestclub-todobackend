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
	@Test
	public void C_validateInserted() throws IOException {
		HttpResponse result = httpCall.get( "/todo/");
		assertEquals("{\"message\":\"Ok!\",\"body\":[{\"id\":1,\"todo\":\"Hire a PO!\"}]}", result.getBody());
		assertEquals(200, result.getHttpStatusCode());
	}
	//TODO Validate getting a single item through id.
	@Test
	public void D_validateGetById() throws IOException {
		HttpResponse result = httpCall.get("/todo/1");
		assertEquals("{\"message\":\"Ok!\",\"body\":{\"id\":1,\"todo\":\"Hire a PO!\"}}", result.getBody());
		assertEquals(200, result.getHttpStatusCode());
	}

	//TODO Delete an item.
	@Test
	public void E_shouldDeleteAnItem() throws IOException {
		HttpResponse result = httpCall.delete("/todo/1");
		assertEquals("{\"message\":\"Ok!\"}", result.getBody());
		assertEquals(200, result.getHttpStatusCode());
	}

	//TODO Get by a non-existing ID

	@Test
	public void F_shouldReturn404IfGetByIdDNE() throws IOException {
		HttpResponse result = httpCall.get("/todo/9001");
		assertEquals("{\"code\":404,\"message\":\"Non existing todo id\"}", result.getBody());
		assertEquals(404, result.getHttpStatusCode());
	}

	//TODO Delete an item that's not there??

	//TODO Add a duplicate todo item
}
