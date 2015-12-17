package com.clubmembershipbackend;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RenewalTest {

	static WebResource webResource1,webResource2;
	
	@BeforeClass
	public static void setup()
	{
		Client client=Client.create();
		webResource1=client.resource("http://localhost:8080/users/renewal/5672cd17f2365ac78e238211");
		webResource2=client.resource("http://localhost:8080/users/renewal/4672cd17f2365ac78e238211");
	}
	
	@Test
	public void testRenewalSuccess() 
	{		
		
		ClientResponse response = webResource1.type("application/json").get(ClientResponse.class);
		
		assertEquals(200,response.getStatus());
	}	
	
	@Test
	public void testRenewalFailed() 
	{		
		
		ClientResponse response = webResource2.type("application/json").get(ClientResponse.class);
		
		assertNotEquals(200,response.getStatus());
	}	
}
