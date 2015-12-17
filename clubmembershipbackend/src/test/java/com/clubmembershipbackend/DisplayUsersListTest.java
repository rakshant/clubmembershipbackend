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
	public void test()  {
		
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		System.out.println(response);
		String output = response.getEntity(String.class);
		System.out.println(output);
		//int output = response.getStatus();
		assertNotEquals(202,output);
		}
	
	/*@Test
	public void testNotNull() throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();		
		WebResource webResource = client.resource("http://localhost:8080/users/userlist");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		int output = response.getStatus();
		assertNotNull(output);
	}*/
	
}
