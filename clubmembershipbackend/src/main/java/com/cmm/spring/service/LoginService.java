package com.cmm.spring.service;



import com.cmm.spring.mongo.collections.UserLogin;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface LoginService  {
	
	

	String login(UserLogin userLogin) throws JsonProcessingException;

	UserLogin logout(String emailId);


	//void delete(String emailId);

}
