package com.clubmembershipbackend;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LoginTest {

	@Before
	void setup(){
		
	}
	
	//new UserRegistration(registration.getFirstName(),registration.getLastName(),registration.getEmailId(), registration.getDateOfBirth(),registration.getMobileNumber(),registration.getOccupation(),new Date(),registration.getPassword(),registration.getStatus(),registration.getUserType()));
	
	@Test
	public void testLogin() {
		
		Client client=Client.create();
		WebResource webResource=client.resource("http://localhost:8080/login");
		
		String loginData = "{\"emailId\":\"ruchi@gmail.com\",\"password\":\"3563\"}";

		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class,loginData);

		
		String output = response.getEntity(String.class);
		assertEquals("{\"emailId\":\"ruchi@gmail.com\",\"password\":\"3563\"}",output);
	}

}
