package com.clubmembershipbackend;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MembershipRenewalTest {

	
	@Before
	public void setup(){
		Client client = Client.create();

	}
	
	@Test
	public void testMembershipRenewal() throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();		
		WebResource webResource = client.resource("http://localhost:8080/users/membershipRenewal?5667014c3113e180ef341a1c");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		int output = response.getStatus();
		assertNotEquals(202,output);
	}
	
	

	
}

