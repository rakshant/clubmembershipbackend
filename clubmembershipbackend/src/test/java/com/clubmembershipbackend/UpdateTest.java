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
		webResource1=client.resource("http://localhost:8080/users/5672a0d9f236e51474eec8bf");
		webResource2=client.resource("http://localhost:8080/users/4672a0d9f236e51474eec8bf"); //invalid object id
		
	}
	
	@Test
	public void testUpdateSuccess()
	{
		String registerData = "{\"mobileNumber\":999999999,\"occupation\":\"Devloper\",\"password\":9999}";
		
		ClientResponse response = webResource1.type("application/json").put(ClientResponse.class,registerData);
		/*System.out.println(response);
		String output = response.getEntity(String.class);
		System.out.println(output);*/
		assertEquals(200,response.getStatus());
	}
	
	@Test
	public void testUpdatefailed()
	{
		
		String registerData = "{\"mobileNumber\":999999999,\"occupation\":\"Devloper\",\"password\":9999}";
		
		ClientResponse response = webResource2.type("application/json").put(ClientResponse.class,registerData);
		
		String output = response.getEntity(String.class);		
		
		assertEquals("",output);
	}
}
