package com.cmm.spring.service;



import java.util.List;

import com.cmm.spring.mongo.collections.UserRegistration;

public interface RegistrationService {

	void save(UserRegistration userRegistration);
	
	List<UserRegistration> read();
	
	//UserLogin login(UserLogin userLogin );
	
	//void delete(String userName);
	
	void update(String userName);
}
