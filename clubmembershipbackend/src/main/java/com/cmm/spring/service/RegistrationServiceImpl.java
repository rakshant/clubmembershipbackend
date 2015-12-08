package com.cmm.spring.service;



import java.util.Iterator;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.rest.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	public void save(UserRegistration userRegistration) {
		registrationRepository.save(userRegistration);
	}


	
	public List<UserRegistration> read() {
		
		System.out.println("using login service");
		return registrationRepository.findAll();
	}
	
/*	public void delete(String userName) {
		List<UserRegistration> temp_users=registrationRepository.findAll();
		Iterator<UserRegistration> users_iterator=temp_users.iterator();
		while(users_iterator.hasNext())
		{
			UserRegistration user=users_iterator.next();
			if(user.getUserName().equals(userName))
			{
				registrationRepository.delete(user.getId());
			}
		}
		
	}
*/
	public void update(String userName) {
		
		
	}


	
	

}
