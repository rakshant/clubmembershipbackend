package com.clubmembershipbackend;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GetBillsByUserTest {

	static WebResource webResource1,webResource2;
	
	@BeforeClass
	public static void setup(){
		Client client = Client.create();
		webResource1 = client.resource("http://localhost:8080/users/bill/5672b565f236f6fd356314f5");
		webResource2 = client.resource("http://localhost:8080/users/bill/4672c14bf23691aa9774293d");
	}
	
	@Test
	public void testSuccess(){
		
		ClientResponse response = webResource1.accept("application/json").get(ClientResponse.class);
		
		assertEquals(200,response.getStatus());
	}
	
	@Test
	public void testNotNull()  {
		
		ClientResponse response = webResource2.accept("application/json").get(ClientResponse.class);
		
		assertNotEquals(200,response.getStatus());
	}

	
}
