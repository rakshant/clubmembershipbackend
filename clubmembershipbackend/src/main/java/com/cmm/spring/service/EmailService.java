package com.cmm.spring.service;

import com.cmm.spring.mongo.collections.UserEmail;
import com.cmm.spring.mongo.collections.UserRegistration;

public interface EmailService {
	
	public void sendEmail(UserRegistration user,UserEmail email);

}
