package com.cmm.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmm.spring.entity.Email;
import com.cmm.spring.entity.Login;
import com.cmm.spring.entity.Registration;
import com.cmm.spring.mongo.collections.UserEmail;
import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.service.EmailService;
import com.cmm.spring.service.LoginService;
import com.cmm.spring.service.RegistrationService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private EmailService emailService;



	@RequestMapping(value="/register", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Registration registerUser(@RequestBody Registration registration) {
		
		registrationService.save(new UserRegistration(registration.getFirstName(),registration.getLastName(),registration.getEmailId(), registration.getDateOfBirth(),registration.getMobileNumber(),registration.getOccupation(),new Date(),registration.getPassword(),registration.getStatus(),registration.getUserType()));
		
		return registration;
	}

	@RequestMapping(value="/viewrequests", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserRegistration> readRequest() {
		List<UserRegistration> userList=registrationService.read();
		return userList;
	}
	
	@RequestMapping(value="/viewdetails", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<UserRegistration>  readUser(@RequestBody Registration registration) {
		 List<UserRegistration>  userList=registrationService.view(new UserRegistration(registration.getFirstName(),registration.getLastName(),registration.getEmailId(), registration.getDateOfBirth(),registration.getMobileNumber(),registration.getOccupation(),new Date(),registration.getPassword(),registration.getStatus(),registration.getUserType()));
		return userList;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String loginUser(@RequestBody Login login) {
		String response = loginService.loginUser(new UserLogin(login.getEmailId(),
				login.getPassword()));
		return "{\"response\":\"" + response + " \"}";
	}
	

	@RequestMapping(value="/mail", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String  sendEmail(@RequestBody Email email ) {
		String response=emailService.sendEmail(new UserRegistration(),new UserEmail());
		return response;
	}

	/*
	 * @RequestMapping(value="/bill", method=RequestMethod.POST,
	 * produces=MediaType.APPLICATION_JSON_VALUE,
	 * consumes=MediaType.APPLICATION_JSON_VALUE) public @ResponseBody Billing
	 * getBill(@RequestBody Billing bill) { billingService.bill(new
	 * UserBilling(bill
	 * .getEmailId(),bill.getUserType(),bill.getFacility(),bill.getAmount()));
	 * return bill; }
	 */

	/*
	 * @RequestMapping(value="/logout", method=RequestMethod.POST,
	 * produces=MediaType.APPLICATION_JSON_VALUE,
	 * consumes=MediaType.APPLICATION_JSON_VALUE) public @ResponseBody void
	 * logoutUser(@RequestBody Login login ) { //loginService.save(new
	 * UserLogin(login.getEmailId(),login.getPassword()));
	 * loginService.delete(login.getEmailId());
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(value="/delete", method=RequestMethod.POST,
	 * produces=MediaType.TEXT_PLAIN_VALUE,
	 * consumes=MediaType.APPLICATION_JSON_VALUE) public @ResponseBody String
	 * deleteUser(@RequestBody Registration registration) {
	 * registrationService.delete(registration.getUserName()); return
	 * "User deleted"; }
	 * 
	 * @RequestMapping(value="/update", method=RequestMethod.POST,
	 * produces=MediaType.TEXT_PLAIN_VALUE,
	 * consumes=MediaType.APPLICATION_JSON_VALUE) public @ResponseBody String
	 * updateUser(@RequestBody Registration registration) {
	 * registrationService.update(registration.getUserName()); return
	 * "User updated"; }
	 */

	// @RequestMapping(value="/delete", method=RequestMethod.POST,
	// produces=MediaType.APPLICATION_JSON_VALUE,
	// consumes=MediaType.APPLICATION_JSON_VALUE)
	// public @ResponseBody Registration deleteUser(@RequestBody Registration
	// registration) {
	// registrationService.delete();
	// return registration;
	// }

}
