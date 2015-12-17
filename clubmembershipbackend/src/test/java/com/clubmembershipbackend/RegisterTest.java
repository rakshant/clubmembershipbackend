package com.clubmembershipbackend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RegisterTest {

	static WebResource webResource;
	
	@BeforeClass
	public static void setup()
	{
		Client client=Client.create();
		webResource=client.resource("http://localhost:8080/users/register");
	}
	
	@Test
	public void testRegisterSuccess() 
	{		
		String registerData = "{\"firstName\":\"john\",\"lastName\":\"D\",\"emailId\":\"ruchi@gmail.com\",\"dateOfBirth\":\"1993-11-12\",\"mobileNumber\":9766919881,\"occupation\":\"engineer\"}";
		
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,registerData);
		
		String output = response.getEntity(String.class);
		
		assertEquals("success",output);
	}	
	
	@Test
	public void testRegisterFailed() 
	{		//This is because same email id will be found in registered hence it will not allow to register again
		String registerData = "{\"firstName\":\"john\",\"lastName\":\"D\",\"emailId\":\"agrawal@gmail.com\",\"dateOfBirth\":\"1993-11-12\",\"mobileNumber\":9766919881,\"occupation\":\"engineer\"}";
		
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,registerData);
		
		String output = response.getEntity(String.class);
		
		assertEquals("failed",output);
	}	
	
	@Test
	public void testRegisterNull() 
	{		
		String registerData = "{\"firstName\":\"john\",\"lastName\":\"D\",\"emailId\":\"\",\"dateOfBirth\":\"1993-11-12\",\"mobileNumber\":9766919881,\"occupation\":\"engineer\"}";
		
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,registerData);
		
		String output = response.getEntity(String.class);
		
		assertEquals("failed",output);
	}	
}
