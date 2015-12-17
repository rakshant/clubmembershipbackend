package com.clubmembershipbackend;



import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CheckUsersEmailTest {

	static WebResource webResource1,webResource2,webResource3;
	
	@BeforeClass
	public static void setup()
	{
		Client client=Client.create();
		webResource1=client.resource("http://localhost:8080/users/check/ruchi@gmail");
		webResource2=client.resource("http://localhost:8080/users/check/abcd@gmail");
		webResource3=client.resource("http://localhost:8080/users/check/null");
	}
	
	@Test
	public void testCheckUserEmailSuccess() 
	{		
	//	String registerData = "{\"firstName\":\"john\",\"lastName\":\"D\",\"emailId\":\"ruchi@gmail.com\",\"dateOfBirth\":\"1993-11-12\",\"mobileNumber\":9766919881,\"occupation\":\"engineer\"}";
		
		ClientResponse response = webResource1.type("application/json").get(ClientResponse.class);
		
		String output = response.getEntity(String.class);
		
		assertEquals("{\"status\":\"failure\"}",output);
	}	
	
	@Test
	public void testCheckUserEmailFailure() 
	{		
	//	String registerData = "{\"firstName\":\"john\",\"lastName\":\"D\",\"emailId\":\"ruchi@gmail.com\",\"dateOfBirth\":\"1993-11-12\",\"mobileNumber\":9766919881,\"occupation\":\"engineer\"}";
		
		ClientResponse response = webResource2.type("application/json").get(ClientResponse.class);
		
		String output = response.getEntity(String.class);
		
		assertEquals("{\"status\":\"success\"}",output);
	}	
	
	@Test
	public void testCheckUserEmailNull() 
	{		
	//	String registerData = "{\"firstName\":\"john\",\"lastName\":\"D\",\"emailId\":\"ruchi@gmail.com\",\"dateOfBirth\":\"1993-11-12\",\"mobileNumber\":9766919881,\"occupation\":\"engineer\"}";
		
		ClientResponse response = webResource3.type("application/json").get(ClientResponse.class);
		
		String output = response.getEntity(String.class);
		
		assertEquals("{\"status\":\"success\"}",output);
	}	
}
