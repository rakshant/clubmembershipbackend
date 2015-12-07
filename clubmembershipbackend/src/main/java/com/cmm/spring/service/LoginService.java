package com.cmm.spring.service;



import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.mongo.collections.UserRegistration;

public interface LoginService {
	
	
	void save(UserLogin userLogin);

	//void delete(String emailId);

}
