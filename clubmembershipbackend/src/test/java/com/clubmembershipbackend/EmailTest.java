package com.clubmembershipbackend;

import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import junit.framework.TestCase;

public class EmailTest extends TestCase
{
	@Test
	public void testEmail()
	{
		Client client=Client.create();
		WebResource webResource=client.resource("http://localhost:8080/mail");
		String registerData="{\"toAddress\":\"agrawal210594@gmail.com\",\"fromAddress\":\"agrawal210594@gmail.com\",\"subject\":\"Test Mail\",\"body\":\"THIS IS A SAMPLE MAIL TO TEST\"}";
		ClientResponse response = webResource.type("application/json")
				   .post(ClientResponse.class,registerData);
		
		assertEquals(200, response.getStatus());
	}	
}
