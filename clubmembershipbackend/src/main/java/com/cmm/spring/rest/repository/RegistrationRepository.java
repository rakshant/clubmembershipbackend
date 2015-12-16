package com.cmm.spring.rest.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cmm.spring.mongo.collections.UserRegistration;

public interface RegistrationRepository extends MongoRepository<UserRegistration, String> {
	
	
	List<UserRegistration> findByEmailId(String emailId); 
	List<UserRegistration> findByStatus(int status);
	
}
