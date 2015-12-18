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

public class FacilityTest {

	static WebResource webResource1,webResource2;
	
	@BeforeClass
	public static void setup(){
		Client client = Client.create();
		webResource1 = client.resource("http://localhost:8080/users/facilities/5672a129f236e51474eec8c1/temporary");
		webResource2 = client.resource("http://localhost:8080/users/facilities/4672a129f236e51474eec8c1/temporary");
	}
	
	@Test
	public void tesFacilitySuccess() {		
		
		ClientResponse response = webResource1.accept("application/json").put(ClientResponse.class);
		
		assertEquals(415,response.getStatus());
	}
	
	@Test
	public void testfacilityFailure() throws JsonParseException, JsonMappingException, IOException {
		
		ClientResponse response = webResource2.accept("application/json").put(ClientResponse.class);
		
		assertEquals(405,response.getStatus());
	}
}
