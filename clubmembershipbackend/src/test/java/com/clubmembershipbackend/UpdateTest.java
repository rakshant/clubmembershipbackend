package com.clubmembershipbackend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UpdateTest 
{
static WebResource webResource1;
static WebResource webResource2;
	
	@BeforeClass
	public static void setup()
	{
		Client client=Client.create();
		webResource1=client.resource("http://localhost:8080/update/56686cacf236fe84be84b6df");
		webResource2=client.resource("http://localhost:8080/update/46686cacf236fe84be84b6df"); //invalid object id
		
	}
	
	@Test
	public void testUpdateSuccess()
	{
		String registerData = "{\"emailId\":\"a@gmail.com\",\"mobileNumber\":999999999,\"occupation\":\"Devloper\",\"password\":9999}";
		
		ClientResponse response = webResource1.type("application/json").post(ClientResponse.class,registerData);
		
		String output = response.getEntity(String.class);
		
		assertNotEquals("{\"id\":null,\"firstName\":\"shruti\"\",\"lastName\":\"kumar Agrawal\",\"emailId\":\"a@gmail.com\",\"dateOfBirth\":\"1993-11-12\",\"mobileNumber\":999999999,\"occupation\":\"Devloper\",\"registeredDate\":\"1993-11-12\",\"password\":\"9999\",\"status\":0,\"userType\":\"New Member\"}",output);
	}
	
	@Test
	public void testUpdatefailed()
	{
		
		String registerData = "{\"emailId\":\"ab@gmail.com\",\"mobileNumber\":999999999,\"occupation\":\"Devloper\",\"password\":9999}";
		
		ClientResponse response = webResource2.type("application/json").post(ClientResponse.class,registerData);
		
		String output = response.getEntity(String.class);
		
		assertEquals("",output);
	}
}
