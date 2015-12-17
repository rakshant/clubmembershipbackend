package com.clubmembershipbackend;


import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ViewPendingRequestTest {

	static WebResource webResource;
	@BeforeClass
	public static void setup(){
		Client client = Client.create();
		webResource = client.resource("http://localhost:8080/users/pendingrequests/5667014c3113e180ef341a1c");
		
	}
	
	@Test
	public void testViewRequestSuccess() throws JsonParseException, JsonMappingException, IOException {
		
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		
		assertEquals(200,response.getStatus());
	}
	
	@Test
	public void testViewRequestFailed() throws JsonParseException, JsonMappingException, IOException {
		
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		
		assertEquals(500,response.getStatus());
	}
	
}
