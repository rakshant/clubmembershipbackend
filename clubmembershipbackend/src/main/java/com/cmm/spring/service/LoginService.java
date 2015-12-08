package com.cmm.spring.service;



import com.cmm.spring.mongo.collections.UserLogin;

public interface LoginService {
	
	
	String save(UserLogin userLogin);

	//void delete(String emailId);

}
