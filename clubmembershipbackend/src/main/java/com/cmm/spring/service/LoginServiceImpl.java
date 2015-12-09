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

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private MongoOperations mongoOperation;

	List<UserRegistration> registrationCheckList;
	List<UserRegistration> loggedInUserList;

	public String loginUser(UserLogin userLogin) {

		Query query = new Query();

		if (userLogin.getEmailId() != null) {

			query.addCriteria(Criteria.where("emailId").regex(
					userLogin.getEmailId()));

		} else
			return "failed";

		if (userLogin.getPassword() != null) {

			query.addCriteria(Criteria.where("password").regex(
					userLogin.getPassword()));

		} else
			return "failed";

		loggedInUserList = mongoOperation.find(query, UserRegistration.class);

		if (loggedInUserList.size() != 0) {
			loginRepository.insert(userLogin);
			for (UserRegistration user : loggedInUserList) {
				System.out.println(user.getUserType());
				return user.getUserType()+":"+user.getId();
			}
		}
		return "failed";

	}

	/*
	 * public void delete(String emailId) {
	 * 
	 * List<UserLogin> temp_users=registrationRepository.findAll();
	 * Iterator<UserRegistration> users_iterator=temp_users.iterator();
	 * while(users_iterator.hasNext()) { UserRegistration
	 * user=users_iterator.next(); if(user.getUserName().equals(userName)) {
	 * registrationRepository.delete(user.getId()); } }
	 * 
	 * }
	 */

}










