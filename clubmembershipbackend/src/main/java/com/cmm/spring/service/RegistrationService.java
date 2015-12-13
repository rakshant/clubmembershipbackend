package com.cmm.spring.service;

import java.util.List;
import com.cmm.spring.entity.Facilities;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RegistrationService {

	String save(UserRegistration userRegistration) throws JsonProcessingException;

	String saveFacility(UserRegistration userRegistration, String id) throws JsonProcessingException;

	List<UserRegistration> read(String id);

	String update(String id, UserRegistration userRegistration) throws JsonProcessingException;

	List<UserRegistration> view(String id);

	void rejectRequest(String email);

	String acceptRequest(String email);

	List<Facilities> bill(String id);

	UserRegistration paymentDone(String id);

	void renewal(String id);
}
