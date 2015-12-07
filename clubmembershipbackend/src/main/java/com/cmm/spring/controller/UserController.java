package com.cmm.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmm.spring.entity.Login;
import com.cmm.spring.entity.Registration;
import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.service.LoginService;
import com.cmm.spring.service.RegistrationService;

@RestController
public class UserController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private LoginService loginService;
	
	
	
	@RequestMapping("/hello")
	public String sayHello(@RequestParam(value="name", defaultValue="Ivan") String name) {
		return "Hello " + name;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Registration registerUser(@RequestBody Registration registration) {
		registrationService.save(new UserRegistration(registration.getFirstName(),registration.getLastName(),registration.getEmailId(), registration.getDateOfBirth(),registration.getMobileNumber(),registration.getOccupation(),new Date()));
		return registration;
	}

	
	@RequestMapping(value="/login", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Login loginUser(@RequestBody Login login ) {
		loginService.save(new UserLogin(login.getEmailId(),login.getPassword()));
		return login;
	}
/*	
	@RequestMapping(value="/logout", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void logoutUser(@RequestBody Login login ) {
		//loginService.save(new UserLogin(login.getEmailId(),login.getPassword()));
		loginService.delete(login.getEmailId());
		
	}*/
	
	
	
	/*@RequestMapping(value="/delete", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteUser(@RequestBody Registration registration) {
		registrationService.delete(registration.getUserName());
		return "User deleted";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateUser(@RequestBody Registration registration) {
		registrationService.update(registration.getUserName());
		return "User updated";
	}
	
	*/
	
	
	

	
	
	
	
//	@RequestMapping(value="/delete", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody Registration deleteUser(@RequestBody Registration registration) {
//		registrationService.delete();
//		return registration;
//	}
	
}
