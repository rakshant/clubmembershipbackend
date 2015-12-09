package com.cmm.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.rest.repository.RegistrationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private MongoOperations mongoOperation;
	
	List<UserRegistration> registrationCheckList;
	List<UserRegistration> registrationRequestUsersList;
	List<UserRegistration> secretaryDetails;
	List<UserRegistration> userDetails;
	List<UserRegistration> permanentUserDetails;
	List<UserRegistration> viewDetailsList;

	public String save(UserRegistration userRegistration) throws JsonProcessingException {
		
		int flag=0;
		ObjectMapper mapper=new ObjectMapper();	
		
		Query query = new Query();		
		
		query.addCriteria(Criteria.where("emailId").regex(userRegistration.getEmailId()));

		registrationCheckList=mongoOperation.find(query, UserRegistration.class);
		
		if(registrationCheckList.size()!=0){
		flag=1;
		}
		
		if(flag==0){
		
			 UserRegistration userReg=registrationRepository.insert(userRegistration);
			String registerJson=mapper.writeValueAsString(userReg);
			return registerJson;
		}
		

		return null;
	}

	public List<UserRegistration> read() {
		 
		Query query = new Query();		
		
		query.addCriteria(Criteria.where("status").is(0));

		registrationRequestUsersList=mongoOperation.find(query, UserRegistration.class);

		return registrationRequestUsersList;
	}

	
	public void update(String userName) {

	}
	
	
public List<UserRegistration> view(String emailId) {
	
		
		viewDetailsList= registrationRepository.findByEmailId(emailId+".com");
		
		System.out.println(viewDetailsList);

		return viewDetailsList;
		
	}


}
