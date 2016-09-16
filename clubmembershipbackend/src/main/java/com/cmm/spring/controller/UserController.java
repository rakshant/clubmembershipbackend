package com.cmm.spring.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cmm.spring.entity.AddOns;
import com.cmm.spring.entity.Facilities;
import com.cmm.spring.entity.Fees;
import com.cmm.spring.entity.HostingCount;
import com.cmm.spring.entity.Login;
import com.cmm.spring.entity.Registration;
import com.cmm.spring.entity.TreasurerBudget;
import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.mongo.collections.UserTreasurerBudget;
import com.cmm.spring.service.BudgetService;
import com.cmm.spring.service.EmailService;
import com.cmm.spring.service.LoginService;
import com.cmm.spring.service.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin
@RestController
@Component
@RequestMapping("/users")
public class UserController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private BudgetService budgetService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private Fees fees;

	@RequestMapping(value = "/file/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public @ResponseBody UserRegistration addFile(
			@PathVariable("id") String id,
			@RequestParam("file") MultipartFile file) throws IOException {
		return registrationService.addFile(id,
				new UserRegistration(file.getBytes()));
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(@PathVariable("id") String id)
			throws FileNotFoundException {

		return registrationService.getFile(id);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String registerUser(
			@RequestBody Registration registration)
			throws JsonProcessingException {
		String result = registrationService.register(new UserRegistration(
				registration.getFirstName(), registration.getLastName(),
				registration.getEmailId(), registration.getDateOfBirth(),
				registration.getMobileNumber(), registration.getOccupation(),
				new Date(), registration.getPassword(), registration
						.getStatus(), registration.getUserType()));
		return result;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, String> loginUser(
			@RequestBody Login login) throws JsonProcessingException {
		String result = loginService.login(new UserLogin(login.getEmailId(),
				login.getPassword()));
		HashMap<String, String> response = new HashMap<String, String>();
		if (result.equals("failed")) {
			response.put("id", "failure");
		} else {
			int colon = result.indexOf(":");
			response.put("id", result.substring(colon + 1));
			response.put("userType", result.substring(0, colon));
		}
		return response;
	}

	@RequestMapping(value = "/membershipRenewal/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> membershipRenewal(@PathVariable("id") String id) {
		
		return registrationService.renewal(id);

	}

	@RequestMapping(value = "/pendingrequests/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserRegistration> getPendingRequestsBySecretary(
			@PathVariable("id") String id) {
		List<UserRegistration> userList = registrationService
				.pendingRequest(id);
		return userList;
	}

	@RequestMapping(value = "/pendingrequests/permanent/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserRegistration> getPendingRequestsBySecretaryForPermanentMember(
			@PathVariable("id") String secretaryId) {
		List<UserRegistration> userList = registrationService
				.getPendingRequestsBySecretaryForPermanentMembers(secretaryId);
		return userList;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserRegistration getUser(@PathVariable("id") String id) {
		UserRegistration user = registrationService.viewDetails(id);
		return user;
	}

	@RequestMapping(value = "/logout/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserLogin logout(@PathVariable("id") String id) {
		UserLogin user = loginService.logout(id);
		return user;
	}

	@RequestMapping(value = "/payment/{id}/{type}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserRegistration payBill(
			@PathVariable("id") String id, @PathVariable("type") String type) {

		return registrationService.payBill(id, type);
	}

	@RequestMapping(value = "/status/apply/permanent/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String applyForPermanentMembership(
			@PathVariable("id") String id) {
		
		return registrationService.applyForPermanentMembership(id);
	}
	
	@RequestMapping(value = "/status/approve/permanent/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String approvePermanentMembershipRequest(
			@PathVariable("id") String id) {
		
		return registrationService.approvePermanentMembershipRequest(id);
	}
	
	@RequestMapping(value = "/status/{statusCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserRegistration> getUsersByStatus(
			@PathVariable("statusCode") String statusCode) {
		
		return registrationService.getUsersByStatus(statusCode);
	}
	
	@RequestMapping(value = "/request", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HashMap<String, String> sendEmail(
			@RequestParam String email, @RequestParam String status) {
		
		return registrationService.request(email,status);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateUser(@PathVariable("id") String id,
			@RequestBody Registration registration)
			throws JsonProcessingException {
		String result = registrationService.update(id, new UserRegistration(
				registration.getMobileNumber(), registration.getOccupation(),
				registration.getPassword()));
		return result;
	}

	@RequestMapping(value = "/treasurer", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, List<HostingCount>> aggregateClubDetails() throws JsonProcessingException, UnknownHostException {
		HashMap<String, List<HostingCount>> hosts=registrationService.aggregationOfClubDetails();

		
		return hosts;
	}

	@RequestMapping(value = "/facilities/{id}/{type}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String reserveFacilities(@PathVariable("id") String id,
			@PathVariable("type") String type,
			@RequestBody Map<String,List<Facilities>> facilities) throws JsonProcessingException, InterruptedException {
		List<Facilities> facility = facilities.get("item");
	
		registrationService.saveFacility(new UserRegistration(facility), id,type);
		return new HashMap<String, String>().put("success","done");
	}

	
	@RequestMapping(value = "/fee/{id}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int getFee(@PathVariable("id") String id,
			@PathVariable("type") String type) throws JsonProcessingException {

		HashMap<String, Integer> hmap = fees.getFee();
		return hmap.get(type);
	}

	@RequestMapping(value = "/bill/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Facilities> getBillsByUser(
			@PathVariable("id") String id) {
		List<Facilities> billList = registrationService.getBillsByUser(id);
		return billList;
	}

	// Active user list viewed by president

	@RequestMapping(value = "/active", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserRegistration> viewlist() {
		List<UserRegistration> userlist = registrationService
				.viewActiveUserList();
		return userlist;
	}

	@RequestMapping(value = "/check/{emailId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> checkUsers(
			@PathVariable("emailId") String emailId) {
		HashMap<String, String> response = new HashMap<String, String>();
		if (registrationService.checkUsersList(emailId)) {

			response.put("status", "failure");
			return response;
		} else {

			response.put("status", "success");
			return response;
		}

	}
	
	@RequestMapping(value = "addons/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateAddOns(@PathVariable("id") String id,@RequestBody AddOns addons)
			throws JsonProcessingException {
		
		Set<AddOns> addOnsList=new HashSet<AddOns>();
		
		addOnsList.add(new AddOns(addons.getName(),addons.getDob(),addons.getRelation()));
		
		registrationService.updateAddOns(new UserRegistration(addOnsList), id);
		
		return new HashMap<String, String>().put("success",
				addons.getRelation());

	}
	
	@RequestMapping(value = "/addons/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Set<AddOns> getAddOns(@PathVariable("id") String id) {
		Set<AddOns> addOnsSet = registrationService.viewAddOnsDetails(id);
		return addOnsSet;
	}
	
	
	@RequestMapping(value = "/renewal/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  HashMap<String, String> checkrenewal(
			@PathVariable("id") String id) {

		return registrationService.checkRenewal(id);
	}
	
	
	@RequestMapping(value = "/budget", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserTreasurerBudget allocateBudget(
			@RequestBody TreasurerBudget budget)
			throws JsonProcessingException {
		UserTreasurerBudget result = budgetService.allocateBudget(new UserTreasurerBudget(budget.getIndoor(),budget.getOutdoor(),budget.getLeisure()));
		return result;
	}
	
	@RequestMapping(value = "/budget", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserTreasurerBudget getAllocateBudget()throws JsonProcessingException {
		UserTreasurerBudget result = budgetService.getAllocateBudget();
		return result;
	}
	
	

}
