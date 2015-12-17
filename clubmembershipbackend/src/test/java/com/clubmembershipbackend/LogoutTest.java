package com.clubmembershipbackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LogoutTest {
	public static WebResource webResource1,webResource2;	
	
@BeforeClass
	public static void setup() {
	Client client = Client.create();
	webResource1 = client.resource("http://localhost:8080/users/logout/5672d9baf2365ac78e23821f");
	webResource2 = client.resource("http://localhost:8080/users/logout/46723b9bf236d256da8969ed");
}

@Test
public void testLogout() {
	
	ClientResponse response = webResource1.type("application/json").get(ClientResponse.class);
	
	String output = response.getEntity(String.class);
	
	System.out.println(output);
	assertEquals("{\"id\":\"5672d9baf2365ac78e23821f\",\"emailId\":\"onkar@gmail.com\",\"password\":\"9088\"}",output);
	
}

@Test
public void testLogoutWithoutLoggedIn() {
	ClientResponse response = webResource2.type("application/json").get(ClientResponse.class);
	
	String request = response.getEntity(String.class);	
	
	assertEquals("",request);
} 

	
	

}