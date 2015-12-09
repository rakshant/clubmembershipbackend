package com.cmm.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.rest.repository.RegistrationRepository;

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

	public UserRegistration save(UserRegistration userRegistration) {
		
		int flag=0;
		
		Query query = new Query();		
		
		query.addCriteria(Criteria.where("emailId").regex(userRegistration.getEmailId()));

		registrationCheckList=mongoOperation.find(query, UserRegistration.class);
		
		if(registrationCheckList.size()!=0){
		flag=1;
		}
		
		if(flag==0){
			
			return registrationRepository.insert(userRegistration);
			
		}

		return null;
	}

	public List<UserRegistration> read() {
		 
		Query query = new Query();		
		
		query.addCriteria(Criteria.where("status").is(0));

		registrationRequestUsersList=mongoOperation.find(query, UserRegistration.class);

		return registrationRequestUsersList;
	}

	/*
	 * public void delete(String userName) { List<UserRegistration>
	 * temp_users=registrationRepository.findAll(); Iterator<UserRegistration>
	 * users_iteratOor=temp_users.iterator(); while(users_iterator.hasNext()) {
	 * UserRegistration user=users_iterator.next();
	 * if(user.getUserName().equals(userName)) {
	 * registrationRepository.delete(user.getId()); } }
	 * 
	 * }
	 */
	public void update(String userName) {

	}
	
	
	public List<UserRegistration> view(UserRegistration userRegistration) {
		
		Query querySecretary = new Query();		
		Query queryUser = new Query();
		Query queryPermanentUser = new Query();	
			
		/*querySecretary.addCriteria(Criteria.where("emailId").regex(userRegistration.getEmailId()));
		querySecretary.addCriteria(Criteria.where("status").is(1));*/
		querySecretary.addCriteria(Criteria.where("userType").regex("secretary"));

		secretaryDetails= mongoOperation.find(querySecretary, UserRegistration.class);
		
		if(mongoOperation.find(querySecretary, UserRegistration.class).size()!=0){
			return secretaryDetails;
		}
		
	/*	queryUser.addCriteria(Criteria.where("emailId").regex(userRegistration.getEmailId()));
		queryUser.addCriteria(Criteria.where("status").is(1));*/
		queryUser.addCriteria(Criteria.where("userType").regex("user"));
		
		userDetails=mongoOperation.find(queryUser, UserRegistration.class);
		
		if(mongoOperation.find(queryUser, UserRegistration.class).size()!=0){
			return userDetails;
		}
		
	/*	queryPermanentUser.addCriteria(Criteria.where("emailId").regex(userRegistration.getEmailId()));
		queryPermanentUser.addCriteria(Criteria.where("status").is(1))*/;
		queryPermanentUser.addCriteria(Criteria.where("userType").regex("permanentUser"));
		
		permanentUserDetails= mongoOperation.find(queryPermanentUser, UserRegistration.class);
		
		if(mongoOperation.find(queryPermanentUser, UserRegistration.class).size()!=0){
			return permanentUserDetails;
		}
		
		
		return null;
		
	}
	

}
