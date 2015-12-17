package com.clubmembershipbackend;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TreasurerTest {

	static WebResource webResource;
	
	@BeforeClass
	public static void setup()
	{
		Client client=Client.create();
		webResource=client.resource("http://localhost:8080/users/treasurer");
	}
	
	@Test
	public void testTreasurerSuccess() 
	{		
		
		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
	
		String output = response.getEntity(String.class);
		
		assertNotEquals(null,output);
	}	
	}

