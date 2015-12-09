package com.clubmembershipbackend;

import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RegisterTest {

	static WebResource webResource;
	
	@BeforeClass
	public void setup()
	{
		Client client=Client.create();
		webResource=client.resource("http://localhost:8080/register");
	}
	
	@Test
	public void testRegisterSuccess() 
	{		
		String registerData = "{\"firstName\":\"john\",\"lastName\":\"D\",\"emailId\":\"ruchi@gmail.com\",\"dateOfBirth\":\"1993-11-12\",\"mobileNumber\":9766919881,\"occupation\":\"engineer\",\"registeredDate\":\"1993-11-12\",\"password\":\"Ruchi\",\"status\":0,\"userType\":\"Ruchi\"}";
		
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,registerData);
		
		String output = response.getEntity(String.class);
		
		assertNotEquals("{\"id\":null,\"firstName\":\"\",\"lastName\":\"D\",\"emailId\":\"ruchi@gmail.com\",\"dateOfBirth\":\"1993-11-12\",\"mobileNumber\":9766919881,\"occupation\":\"engineer\",\"registeredDate\":\"1993-11-12\",\"password\":\"Ruchi\",\"status\":0,\"userType\":\"Ruchi\"}",output);
	}	
}
