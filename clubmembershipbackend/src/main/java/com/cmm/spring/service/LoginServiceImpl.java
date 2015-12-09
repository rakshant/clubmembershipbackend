package com.cmm.spring.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.rest.repository.LoginRepository;
import com.cmm.spring.rest.repository.RegistrationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginServiceImpl implements LoginService {
	
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private MongoOperations mongoOperation;
	
	
	 List<UserRegistration> userList;

	public String loginUser(UserLogin userLogin) throws JsonProcessingException {
		String response;
		Query query = new Query();	
		ObjectMapper mapper=new ObjectMapper();
		/*String response = loginService.loginUser(new UserLogin(login.getEmailId(),
				login.getPassword()));
		String loginJson=mapper.writeValueAsString(response);
		return loginJson;
		*/
		if(userLogin.getEmailId()!=null){
		
		query.addCriteria(Criteria.where("emailId").regex(userLogin.getEmailId()));
		
		}
		
		else 
			response="{\"response\":\"failed\"}";
		
		if(userLogin.getPassword()!=null){
		
		query.addCriteria(Criteria.where("password").regex(userLogin.getPassword()));
		
		}
		else response="{\"response\":\"failed\"}";
		
		userList=mongoOperation.find(query, UserRegistration.class);
		
		if(userList.size()!=0){
		
		loginRepository.insert(userLogin);
		
		response= "success";
		
		}
		
		response= "{\"response\":\"failed\"}";
		
		
		String loginJson=mapper.writeValueAsString(response);
		return loginJson;
		 
	}


	/*public void delete(String emailId) {
		
		List<UserLogin> temp_users=registrationRepository.findAll();
		Iterator<UserRegistration> users_iterator=temp_users.iterator();
		while(users_iterator.hasNext())
		{
			UserRegistration user=users_iterator.next();
			if(user.getUserName().equals(userName))
			{
				registrationRepository.delete(user.getId());
			}
		}
		
	}*/
	
	
	
	

}
