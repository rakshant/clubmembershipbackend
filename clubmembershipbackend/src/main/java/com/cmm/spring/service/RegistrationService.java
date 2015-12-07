package com.cmm.spring.service;



import java.util.List;

import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.mongo.collections.UserRegistration;

public interface RegistrationService {

	void save(UserRegistration userRegistration);
	
	UserRegistration read(String emailId);
	
	//UserLogin login(UserLogin userLogin );
	
	//void delete(String userName);
	
	void update(String userName);
}
