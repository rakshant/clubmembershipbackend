package com.cmm.spring.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.cmm.spring.entity.AddOns;
import com.cmm.spring.entity.Facilities;
import com.cmm.spring.entity.HostingCount;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RegistrationService {

	UserRegistration addFile(String id,UserRegistration file) throws IOException;
	ResponseEntity<byte[]> getFile(String id) throws FileNotFoundException;
	
	String register(UserRegistration userRegistration) throws JsonProcessingException;

	String saveFacility(UserRegistration userRegistration, String id,String type) throws JsonProcessingException, InterruptedException;

	List<UserRegistration> pendingRequest(String id);

	String update(String id, UserRegistration userRegistration) throws JsonProcessingException;
	
	public  HashMap<String,List<HostingCount>> aggregationOfClubDetails() throws UnknownHostException;

	UserRegistration viewDetails(String id);

	void rejectRequest(String email);

	String acceptRequest(String email);

	List<Facilities> getBillsByUser(String id);

	UserRegistration payBill(String id, String type);
	

	HashMap<String, String> renewal(String id);
	boolean checkUsersList(String emailId);
	
	List<UserRegistration>  viewActiveUserList();
	String updateAddOns(UserRegistration userRegistration, String id) throws JsonProcessingException;
	Set<AddOns> viewAddOnsDetails(String id);
	HashMap<String, String> checkRenewal(String id);
	HashMap<String, String> request(String email,String status);
}
