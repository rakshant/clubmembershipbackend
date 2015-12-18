package com.clubmembershipbackend;


import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MembershipRenewalTest {

	static WebResource webResource1,webResource2;
	
	@BeforeClass
	public static void setup(){
		Client client = Client.create();
		webResource1 = client.resource("http://localhost:8080/users/membershipRenewal?5672a129f236e51474eec8c1");
		webResource2 = client.resource("http://localhost:8080/users/membershipRenewal?4672a129f236e51474eec8c2");
	}
	
	@Test
	public void testMembershipRenewalSuccess() throws JsonParseException, JsonMappingException, IOException {
		ClientResponse response = webResource1.accept("application/json").get(ClientResponse.class);
		assertEquals(200,response.getStatus());
	}
		
}

