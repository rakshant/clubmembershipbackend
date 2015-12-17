package com.clubmembershipbackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LoginTest {

	static WebResource webResource;
	
	@BeforeClass
	public static void setup()
	{
		Client client=Client.create();
		webResource=client.resource("http://localhost:8080/users/login");
	}	
	@Test
	public void testLoginSuccess() 
	{		
		String loginData = "{\"emailId\":\"agrawal@gmail.com\",\"password\":\"2364\"}";

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,loginData);
		
		String output = response.getEntity(String.class);
		
		assertEquals("{\"id\":\"5672a0d9f236e51474eec8bf\",\"userType\":\"User\"}",output);
	}	

	@Test
	public void testLoginFailed() 
	{		
		String loginData = "{\"emailId\":\"agrawal210594@gmail.com\",\"password\":\"2992\"}";

		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class,loginData);
		
		String output = response.getEntity(String.class);
		
		assertEquals("{\"id\":\"failure\"}",output);
	}
	
	@Test
	public void testLoginNull() 
	{		
		String loginData = "{\"emailId\":null,\"password\":null}";

		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class,loginData);
		
		String output = response.getEntity(String.class);
		
		assertEquals("{\"id\":\"failure\"}",output);
	}
}



