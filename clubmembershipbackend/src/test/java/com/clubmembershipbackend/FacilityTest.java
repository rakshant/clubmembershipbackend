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

public class FacilityTest {

	
	@Before
	public void setup(){
		Client client = Client.create();

	}
	
	@Test
	public void tesFacilitySuccess() throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();		
		WebResource webResource = client.resource("http://localhost:8080/users/facilities?56679cf2311368e49a1ea89b");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		int output = response.getStatus();
		assertNotEquals(202,output);
	}
	
	@Test
	public void testfacilityNotNull() throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();		
		WebResource webResource = client.resource("http://localhost:8080/users/facilities?56679cf2311368e49a1ea89b");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		int output = response.getStatus();
		assertNotNull(output);
	}
}