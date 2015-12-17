package com.clubmembershipbackend;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class AddonsTest {

	static WebResource webResource1,webResource2;
	
	@BeforeClass
	public static void setup()
	{
		Client client=Client.create();
		webResource1=client.resource("http://localhost:8080/users/addons/5672cd17f2365ac78e238211");
		webResource2=client.resource("http://localhost:8080/users/addons/4672cd17f2365ac78e238211");
	}
	
	@Test
	public void testRegisterSuccess() 
	
	{		
		String registerData = "{\"name\":\"john\",\"dob\":\"1993-11-12\",\"relation\":\"Father\"}";
		
		ClientResponse response = webResource1.type("application/json").put(ClientResponse.class,registerData);
		
		assertEquals(200,response.getStatus());
	}
	
	@Test
	public void testRegisterFailed() 
	
	{		
		String registerData = "{\"name\":\"john\",\"dob\":\"1993-11-12\",\"relation\":\"Father\"}";
		
		ClientResponse response = webResource2.type("application/json").put(ClientResponse.class,registerData);
		
		assertEquals(500,response.getStatus());
	}	
	
	
}
