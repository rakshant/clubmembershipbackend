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

	public List<UserRegistration> read(String id) {
		
		
		if(isSecretary(id)){
		 
		Query query = new Query();		
		
		query.addCriteria(Criteria.where("status").is(0));

		registrationRequestUsersList=mongoOperation.find(query, UserRegistration.class);

		return registrationRequestUsersList;
		
		}
		return null;
	}

	
	private boolean isSecretary(String id) {
		
		UserRegistration user=registrationRepository.findOne(id);
		if(user.getUserType().equals("secretary")){
			return true;
		}
		
		return false;
	}

	public String update(String id,UserRegistration userRegistration) throws JsonProcessingException
	{
		
		viewDetailsList= registrationRepository.findById(id);
		
		ObjectMapper objectMapper=new ObjectMapper();
	
		UserRegistration usreg=new UserRegistration();
		
		
		if(viewDetailsList.size()!=0)
		{	
			usreg=viewDetailsList.get(0);
			//Setting all the old details of that document 
			userRegistration.setFirstName(usreg.getFirstName());
			userRegistration.setLastName(usreg.getLastName());
			userRegistration.setDateOfBirth(usreg.getDateOfBirth());
			userRegistration.setLastName(usreg.getLastName());
			userRegistration.setRegisteredDate(usreg.getRegisteredDate());
			userRegistration.setId(usreg.getId());
			
			registrationRepository.delete(usreg);
			usreg=registrationRepository.save(userRegistration);
			
			String registerJson=objectMapper.writeValueAsString(usreg);		
			
			return registerJson;
		}
		else
		{			
			return null;
		}
	}

	
	
public List<UserRegistration> view(String id) {
		
		
		viewDetailsList= registrationRepository.findById(id);

		return viewDetailsList;
		
	}

public void rejectRequest(String email) {
	System.out.println("message received to reject request"+email);
}

public void acceptRequest(String email) {
	System.out.println("message received to accept request"+email);
}


}
