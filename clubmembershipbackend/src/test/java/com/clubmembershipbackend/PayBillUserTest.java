package com.clubmembershipbackend;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PayBillUserTest {

	static WebResource webResource1;
	static WebResource webResource2;

	
	@BeforeClass
	public static void setup(){
		Client client = Client.create();
		webResource1=client.resource("http://localhost:8080/users/payment/5672c14bf23691aa9774293d/User");
		webResource2 = client.resource("http://localhost:8080/users/payment/6572c14bf23691aa9774293d/User");
		
	}
	
	@Test
	public void testBillPaySuccess() throws JsonParseException, JsonMappingException, IOException {
			
		
		ClientResponse response = webResource1.accept("application/json").put(ClientResponse.class);
		
		//String output = response.getEntity(String.class);
		
		assertNotEquals(200,response.getStatus());
	}
	
	@Test
	public void testBillPayFailure() throws JsonParseException, JsonMappingException, IOException {
			
		
		ClientResponse response = webResource2.accept("application/json").put(ClientResponse.class);
		
		//String output = response.getEntity(String.class);
		
		
		assertEquals(500,response.getStatus());
	}	
}
