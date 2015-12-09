package com.cmm.spring.service;



import com.cmm.spring.mongo.collections.UserLogin;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface LoginService  {
	
	

	String loginUser(UserLogin userLogin) throws JsonProcessingException;


	//void delete(String emailId);

}
