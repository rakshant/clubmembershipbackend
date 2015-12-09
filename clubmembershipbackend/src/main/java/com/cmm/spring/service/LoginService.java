package com.cmm.spring.service;



import com.cmm.spring.mongo.collections.UserLogin;

public interface LoginService {
	
	

	String loginUser(UserLogin userLogin);


	//void delete(String emailId);

}
