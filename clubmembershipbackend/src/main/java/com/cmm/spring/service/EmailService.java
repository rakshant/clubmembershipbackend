package com.cmm.spring.service;

import com.cmm.spring.mongo.collections.UserEmail;

public interface EmailService {
	
	public void sendEmail(UserEmail email);

}
