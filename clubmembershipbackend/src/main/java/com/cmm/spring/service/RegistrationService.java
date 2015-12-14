package com.cmm.spring.service;

import java.util.List;
import com.cmm.spring.entity.Facilities;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RegistrationService {

	String register(UserRegistration userRegistration) throws JsonProcessingException;

	String saveFacility(UserRegistration userRegistration, String id) throws JsonProcessingException;

	List<UserRegistration> pendingRequest(String id);

	String update(String id, UserRegistration userRegistration) throws JsonProcessingException;

	UserRegistration viewDetails(String id);

	void rejectRequest(String email);

	String acceptRequest(String email);

	List<Facilities> billByUser(String id);

	UserRegistration payBill(String id);

	void renewal(String id);
}
