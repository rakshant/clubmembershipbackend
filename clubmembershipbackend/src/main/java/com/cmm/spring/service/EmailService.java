package com.cmm.spring.service;

import com.cmm.spring.mongo.collections.UserEmail;
import com.cmm.spring.mongo.collections.UserRegistration;

public interface EmailService {
	
	public String sendEmail(UserRegistration user,UserEmail email);

}
