package com.cmm.spring.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cmm.spring.entity.Facilities;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RegistrationService {

	UserRegistration addFile(String id,UserRegistration file) throws IOException;
	ResponseEntity<byte[]> getFile(String id) throws FileNotFoundException;
	
	String register(UserRegistration userRegistration) throws JsonProcessingException;

	String saveFacility(UserRegistration userRegistration, String id,String type) throws JsonProcessingException;

	List<UserRegistration> pendingRequest(String id);

	String update(String id, UserRegistration userRegistration) throws JsonProcessingException;
	
	public List<com.cmm.spring.entity.HostingCount> aggregationOfType();

	UserRegistration viewDetails(String id);

	void rejectRequest(String email);

	String acceptRequest(String email);

	List<Facilities> getBillsByUser(String id);

	UserRegistration payBill(String id, String type);

	boolean renewal(String id);
	String checkUsersList(String emailId);
	
	List<UserRegistration>  viewActiveUserList();
}
