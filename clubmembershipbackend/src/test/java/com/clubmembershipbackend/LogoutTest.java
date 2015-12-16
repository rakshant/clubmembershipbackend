package com.clubmembershipbackend;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class LogoutTest {
	static Client client;
	WebResource webResource;	
	
@BeforeClass
	public static void setup() {
	client = Client.create();
}

@Test
public void testLogout() {
	webResource = client
			.resource("http://localhost:8080/users/logout?5667014c3113e180ef341a1c");
	String request = webResource.get(String.class);
	assertNotNull(request);
}

@Test
public void testLogoutWithoutLoggedIn() {
	webResource = client
			.resource("http://localhost:8080/users/logout?id=5667014c3113e180ef341a1c");
	String request = webResource.get(String.class);
	Assert.assertEquals("",request);
} 

	
	

}