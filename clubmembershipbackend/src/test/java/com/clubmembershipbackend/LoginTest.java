package com.clubmembershipbackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LoginTest {

	static WebResource webResource;
	
	@BeforeClass
	public void setup()
	{
		Client client=Client.create();
		webResource=client.resource("http://localhost:8080/login");
	}	
	@Test
	public void testLoginFailed() 
	{		
		String loginData = "{\"emailId\":\"agrawal@gmail.com\",\"password\":\"1955\"}";

		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class,loginData);
		
		String output = response.getEntity(String.class);
		System.out.println(output);
		assertEquals("{\"response\":\"failed\"}",output);
	}	

	@Test
	public void testLoginSuccess() 
	{		
		String loginData = "{\"emailId\":\"agrawal210594@gmail.com\",\"password\":\"2992\"}";

		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class,loginData);
		
		String output = response.getEntity(String.class);
		
		assertNotEquals("{\"id\":null,\"emailId\":\"agrawal210594@gmail.com\",\"password\":\"2992\"}",output);
	}
}



