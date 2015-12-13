package com.cmm.spring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmm.spring.entity.Facilities;
import com.cmm.spring.entity.Fees;
import com.cmm.spring.entity.Login;
import com.cmm.spring.entity.Registration;
import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.service.EmailService;
import com.cmm.spring.service.LoginService;
import com.cmm.spring.service.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin
@RestController
@Component
public class UserController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String registerUser(
			@RequestBody Registration registration)
			throws JsonProcessingException {
		String result = registrationService.save(new UserRegistration(
				registration.getFirstName(), registration.getLastName(),
				registration.getEmailId(), registration.getDateOfBirth(),
				registration.getMobileNumber(), registration.getOccupation(),
				new Date(), registration.getPassword(), registration
						.getStatus(), registration.getUserType()));
		return result;
	}

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    
  //  @Scheduled(fixedRate = 5000)
	@RequestMapping(value = "/membershipRenewal/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void membershipRenewal(@PathVariable("id") String id){
		registrationService.renewal(id);
		System.out.println("membership renewed!");
	}
    
/*    @RequestMapping(value = "/membershipRenewal/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserRegistration> membershipRenewal(
			@PathVariable("id") String id) {
		List<UserRegistration> userList = registrationService.read(id);
		return userList;
	}    */

	@RequestMapping(value = "/viewrequests/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserRegistration> readRequest(
			@PathVariable("id") String id) {
		List<UserRegistration> userList = registrationService.read(id);
		return userList;
	}

	@RequestMapping(value = "/viewdetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserRegistration> readUser(
			@PathVariable("id") String id) {
		List<UserRegistration> userList = registrationService.view(id);
		return userList;
	}

	@RequestMapping(value = "/logout/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserLogin logout(@PathVariable("id") String id) {
		UserLogin user = loginService.logout(id);
		return user;
	}

	@RequestMapping(value = "/paymentdone/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserRegistration paymentDone(
			@PathVariable("id") String id) {
		return registrationService.paymentDone(id);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, String> loginUser(
			@RequestBody Login login) throws JsonProcessingException {
		String result = loginService.loginUser(new UserLogin(
				login.getEmailId(), login.getPassword()));
		HashMap<String, String> response = new HashMap<String, String>();
		if (result.equals("failed")) {
			response.put("id", "failure");
		} else {
			int colon = result.indexOf(":");
			response.put("id", result.substring(colon + 1));
			response.put("userType", result.substring(0, colon));
			System.out.println(result);
		}
		return response;
	}

	/*
	 * @RequestMapping(value="/mail",
	 * method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,
	 * consumes=MediaType.APPLICATION_JSON_VALUE) public @ResponseBody String
	 * sendEmail(@RequestBody Email email ) { String
	 * response=emailService.sendEmail(new UserRegistration(),new UserEmail());
	 * return response; }
	 */

	@RequestMapping(value = "/processrequest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HashMap<String, String> sendEmail(
			@RequestParam String email, @RequestParam String status) {
		HashMap<String, String> response = new HashMap<String, String>();
		String result = "";
		if (status.equals("accept")) {

			result = registrationService.acceptRequest(email);
			response.put("status", result);
			response.put("message", "Request Accepted Successfully");
		} else {
			registrationService.rejectRequest(email);
			response.put("status", result);
			response.put("message", "Request Rejected Successfully");
		}
		return response;
	}

	// Update will update mobileNo,occupation,password through emailId
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateUser(@PathVariable("id") String id,
			@RequestBody Registration registration)
			throws JsonProcessingException {
		String result = registrationService.update(id, new UserRegistration(
				registration.getMobileNumber(), registration.getOccupation(),
				registration.getPassword()));
		return result;
	}

	@RequestMapping(value = "/paymentFacilities/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String reserveIndoorFacilities(@PathVariable("id") String id,
			@RequestBody Facilities facilities) throws JsonProcessingException {
		
		System.out.println(id + " " + facilities);
		List<Facilities> facility = new ArrayList<Facilities>();
		facility.add(new Facilities(facilities.getCategory(), facilities
				.getType(), facilities.getPrice()));
		System.out.println("------>" + facility);
	
		registrationService.saveFacility(new UserRegistration(facility), id);		
		return new HashMap<String, String>().put("success", facilities.getCategory());

	}

	@RequestMapping(value = "/getFee/{id}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int getFee(@PathVariable("id") String id,
			@PathVariable("type") String type) throws JsonProcessingException {
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:Mail-bean.xml");
		Fees fee = (Fees) context.getBean("feeStructure");		
		HashMap<String, Integer> hmap = fee.getFee();
		return hmap.get(type);
	}

	@RequestMapping(value = "/viewbillingdetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Facilities> billUser(
			@PathVariable("id") String id) {
		List<Facilities> billList = registrationService.bill(id);
		return billList;
	}

}
