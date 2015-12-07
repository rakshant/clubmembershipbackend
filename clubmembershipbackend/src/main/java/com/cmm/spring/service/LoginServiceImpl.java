package com.cmm.spring.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.rest.repository.LoginRepository;
import com.cmm.spring.rest.repository.RegistrationRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	
	@Autowired
	private LoginRepository loginRepository;

	public void save(UserLogin userLogin) {

		 loginRepository.save(userLogin);
	}

	/*public void delete(String emailId) {
		
		List<UserLogin> temp_users=registrationRepository.findAll();
		Iterator<UserRegistration> users_iterator=temp_users.iterator();
		while(users_iterator.hasNext())
		{
			UserRegistration user=users_iterator.next();
			if(user.getUserName().equals(userName))
			{
				registrationRepository.delete(user.getId());
			}
		}
		
	}*/
	
	
	
	

}
