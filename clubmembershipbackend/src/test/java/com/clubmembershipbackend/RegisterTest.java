package com.clubmembershipbackend;

import java.util.Date;





import org.junit.Before;
import org.junit.Test;

import com.cmm.spring.mongo.collections.UserRegistration;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import junit.framework.TestCase;

public class RegisterTest extends TestCase {
	
	@Before
	void setup(){
		
	}
	
	//new UserRegistration(registration.getFirstName(),registration.getLastName(),registration.getEmailId(), registration.getDateOfBirth(),registration.getMobileNumber(),registration.getOccupation(),new Date(),registration.getPassword(),registration.getStatus(),registration.getUserType()));
	
	@Test
	public void testRegister() {
		
		Client client=Client.create();
		WebResource webResource=client.resource("http://localhost:8080/register");
		
		String registerData = "{\"firstName\":\"Ruchi\",\"lastName\":\"D\",\"emailId\":\"ruchi@gmail.com\",\"dateOfBirth\":\"1993-11-12\",\"mobileNumber\":9766919881,\"occupation\":\"engineer\",\"registeredDate\":\"1993-11-12\",\"password\":\"Ruchi\",\"status\":0,\"userType\":\"Ruchi\"}";

		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class,registerData);

		
		String output = response.getEntity(String.class);
		assertEquals("success",output);
	}

}
