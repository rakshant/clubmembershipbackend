package com.cmm.spring.service;



import java.util.List;

//import com.cmm.spring.entity.IndoorFacilities;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RegistrationService {


	String save(UserRegistration userRegistration) throws JsonProcessingException;
	
	String saveFacility(UserRegistration userRegistration, String id) throws JsonProcessingException;
	
	List<UserRegistration> read(String id);
	
	//UserLogin login(UserLogin userLogin );
	
	//void delete(String userName);
	
	String update(String id,UserRegistration userRegistration) throws JsonProcessingException;


	 List<UserRegistration>  view(String id);
	 
	void rejectRequest(String email);
	String acceptRequest(String email);

	

	UserRegistration paymentDone(String id);

	//IndoorFacilities addIndoorFacilities(String id,int tableTennisFees,int badmintonFees,IndoorFacilities indoorFacilities);

	
}
