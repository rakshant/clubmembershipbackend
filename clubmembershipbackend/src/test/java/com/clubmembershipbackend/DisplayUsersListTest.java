package com.clubmembershipbackend;


import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DisplayUsersListTest {

	public static WebResource webResource;
	@BeforeClass
	public static void setup(){
		Client client = Client.create();
		webResource = client.resource("http://localhost:8080/users/5672a129f236e45474eec8c34");
	}
	
	@Test
	public void testSuccess()  {
		
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		
		String output = response.getEntity(String.class);
		
		assertNotEquals(202,output);
	}
	
}
