package com.clubmembershipbackend;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class AllocateBudgetGetTest {

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
		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
		
		String output = response.getEntity(String.class);
	
		assertNotEquals(null,output);
	}	
	
	//this test will pass if budgetAllocation db is not present
	@Test
	public void testBudgetAllocateFailed() 
	{			
		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
	
		String output = response.getEntity(String.class);
	
		assertEquals("",output);
	}	

}



