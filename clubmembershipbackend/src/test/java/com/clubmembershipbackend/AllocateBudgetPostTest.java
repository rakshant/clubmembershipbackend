package com.clubmembershipbackend;


import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class AllocateBudgetPostTest {

	static WebResource webResource;
	
	@BeforeClass
	public static void setup()
	{
		Client client=Client.create();
		webResource=client.resource("http://localhost:8080/users/budget");
	}	
	
	@Test
	public void testBudgetAllocateSuccess() 
	{		
		String loginData = "{\"indoor\":5000,\"outdoor\":2000,\"leisure\":3000}";

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,loginData);
	
		String output = response.getEntity(String.class);
	
		assertNotEquals(null,output);
	}	

}



