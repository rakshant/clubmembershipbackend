package com.cmm.spring.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.cmm.spring.entity.AddOns;
import com.cmm.spring.entity.Facilities;
import com.cmm.spring.entity.HostingCount;
import com.cmm.spring.mongo.collections.UserEmail;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.rest.repository.RegistrationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private MongoOperations mongoOperation;

	@Autowired
	private MailSender mailSender;

	List<UserRegistration> registrationCheckList;
	List<UserRegistration> registrationRequestUsersList;
	List<UserRegistration> secretaryDetails;
	List<UserRegistration> userDetails;
	List<UserRegistration> permanentUserDetails;
	List<UserRegistration> viewDetailsList;
	List<UserRegistration> userList;

	public String register(UserRegistration userRegistration)
			throws JsonProcessingException {

		int flag = 0;

		Query query = new Query();

		query.addCriteria(Criteria.where("emailId").is(
				userRegistration.getEmailId()));

		registrationCheckList = mongoOperation.find(query,
				UserRegistration.class);
		if (registrationCheckList.size() != 0) {
			flag = 1;

			return "{ \"error\":\"Email already registered\" }";
		}

		if (flag == 0) {

			Date currentDate = new Date();
			Date enteredDate = userRegistration.getDateOfBirth();
			Calendar calender = Calendar.getInstance();

			int currentYear = calender.get(Calendar.YEAR);

			calender.setTime(enteredDate);

			int enteredYear = calender.get(calender.YEAR);

			if (currentDate.compareTo(enteredDate) != -1) {

				if (currentYear - enteredYear > 18) {

					userRegistration.setPreviousRenewalTime(userRegistration
							.getRegisteredDate().getTime());
					registrationRepository.insert(userRegistration);
					return "{ \"result\":\"success\" }";
				}
				else {
					return "{ \"result\":\"Error- Age should be minimum 18 years\" }";
				}
			}
			else {
				return "{ \"result\":\"Error- Date of birth can not be current or future date\" }";
			}
		}

		return "{ \"result\":\"failed\" }";
	}

	public List<UserRegistration> pendingRequest(String id) {

		if (isSecretary(id)) {

			Query query = new Query();

			query.addCriteria(Criteria.where("status").is(0));

			registrationRequestUsersList = mongoOperation.find(query,
					UserRegistration.class);

			return registrationRequestUsersList;

		}
		return null;
	}

	public List<UserRegistration> getPendingRequestsBySecretaryForPermanentMembers(String secretaryId) {
		if (isSecretary(secretaryId)) {
			Query query = new Query();
			query.addCriteria(Criteria.where("status").is(3)); //Pending requests made by permanent members only.
			registrationRequestUsersList = mongoOperation.find(query,
					UserRegistration.class);
			return registrationRequestUsersList;
		}
		return null;
	}
	
	private boolean isSecretary(String id) {

		UserRegistration user = registrationRepository.findOne(id);
		if (user.getUserType().equals("Secretary")) {
			return true;
		}

		return false;
	}

	public String update(String id, UserRegistration userRegistration)
			throws JsonProcessingException {

		UserRegistration user = new UserRegistration();

		user = registrationRepository.findOne(id);

		ObjectMapper objectMapper = new ObjectMapper();

		if (user != null) {
			if (userRegistration.getOccupation() != null)
				user.setOccupation(userRegistration.getOccupation());

			if (userRegistration.getMobileNumber() != 0)
				user.setMobileNumber(userRegistration.getMobileNumber());

			if (userRegistration.getPassword() != null)
				user.setPassword(userRegistration.getPassword());

			user = registrationRepository.save(user);

			String registerJson = objectMapper.writeValueAsString(user);

			return registerJson;
		} else {
			return null;
		}
	}

	public HashMap<String, List<HostingCount>> aggregationOfClubDetails()
			throws UnknownHostException {

		List<HostingCount> indoor_hostingCountList = new ArrayList<HostingCount>();
		List<HostingCount> outdoor_hostingCountList = new ArrayList<HostingCount>();
		List<HostingCount> leisure_hostingCountList = new ArrayList<HostingCount>();
		HashMap<String, List<HostingCount>> hashmap = new HashMap<String, List<HostingCount>>();

		// List<HostingCount> hostingCountList=new ArrayList<HostingCount>();
		HostingCount hostingCountObj = new HostingCount(); // this is a temp
															// object of our
															// pojo class
		List<DBObject> dbList = new ArrayList<DBObject>();

		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB("test");
		DBCollection ledger = db.getCollection("user_registrations");
		DBObject unwind = new BasicDBObject("$unwind", "$facilities");
		dbList.add(unwind);
		DBObject projectFields = new BasicDBObject("Total_Fee",
				"$facilities.price");
		projectFields.put("Category", "$facilities.category");
		projectFields.put("Name", "$facilities.type");
		DBObject project = new BasicDBObject("$project", projectFields);
		dbList.add(project);
		DBObject groupFields = new BasicDBObject("_id", "$Name");
		groupFields.put("TotalFee", new BasicDBObject("$sum", "$Total_Fee"));
		DBObject group = new BasicDBObject("$group", groupFields);
		dbList.add(group); // This is adding parameters in DbObjectList

		AggregationOutput output = ledger.aggregate(dbList);

		Iterable<DBObject> iterable = output.results();
		Iterator<DBObject> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			DBObject dbobj = iterator.next();

			hostingCountObj = mongoOperation.getConverter().read(
					HostingCount.class, dbobj);

			if (hostingCountObj.getType().equals("tableTennis")
					|| hostingCountObj.getType().equals("badminton")
					|| hostingCountObj.getType().equals("billiards")
					|| hostingCountObj.getType().equals("healthClub")
					|| hostingCountObj.getType().equals("squash"))
				indoor_hostingCountList.add(hostingCountObj);

			else if (hostingCountObj.getType().equals("lawnTennis")
					|| hostingCountObj.getType().equals("swimming")
					|| hostingCountObj.getType().equals("cricket")
					|| hostingCountObj.getType().equals("playground"))
				outdoor_hostingCountList.add(hostingCountObj);

			else if (hostingCountObj.getType().equals("cardRoom")
					|| hostingCountObj.getType().equals("library")
					|| hostingCountObj.getType().equals("restaurantBar")
					|| hostingCountObj.getType().equals("banquetHall")
					|| hostingCountObj.getType().equals("conferenceHall"))
				leisure_hostingCountList.add(hostingCountObj);

		}

		if (indoor_hostingCountList.size() < 5) {
			for (int i = indoor_hostingCountList.size(); i < 5; i++)
				indoor_hostingCountList.add(new HostingCount());
		}

		if (outdoor_hostingCountList.size() < 4) {
			for (int i = outdoor_hostingCountList.size(); i < 4; i++)
				outdoor_hostingCountList.add(new HostingCount());
		}

		if (leisure_hostingCountList.size() < 5) {
			for (int i = leisure_hostingCountList.size(); i < 5; i++)
				leisure_hostingCountList.add(new HostingCount());
		}

		hashmap.put("indoor", indoor_hostingCountList);
		hashmap.put("outdoor", outdoor_hostingCountList);
		hashmap.put("leisure", leisure_hostingCountList);

		return hashmap;
	}

	public UserRegistration addFile(String id, UserRegistration file)
			throws IOException {
		UserRegistration user = new UserRegistration();
		user = registrationRepository.findOne(id);

		if (user != null) {
			user.setBytes(file.getBytes(), true);
			registrationRepository.save(user);
			return user;
		} else
			return null;
	}

	public ResponseEntity<byte[]> getFile(String id)
			throws FileNotFoundException {
		UserRegistration user = new UserRegistration();
		user = registrationRepository.findOne(id);
		if (user != null) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
					.body(user.getBytes());

		} else {
			return null;
		}
	}

	public UserRegistration viewDetails(String id) {
		UserRegistration viewDetails = registrationRepository.findOne(id);
		return viewDetails;
	}

	public void rejectRequest(String email) {

		UserRegistration user = registrationRepository.findByEmailId(email)
				.get(0);
		registrationRepository.delete(user);
	}

	public String acceptRequest(String email) {

		UserRegistration user = registrationRepository.findByEmailId(email)
				.get(0);
		String id = user.getId();

		UserEmail userEmail = new UserEmail();
		userEmail.setFromAddress("clubmembershipuser@gmail.com");
		userEmail.setToAddress(email);
		userEmail.setSubject("ClubMembership: Entrance fee amount payment");

		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String emailID = user.getEmailId();
		String occupation = user.getOccupation();
		Long mobile = user.getMobileNumber();

		user.setStatus(1);

		registrationRepository.save(user);

		userEmail
				.setBody("Please pay the Entrance fee amount of Rs. "
						+ 1000
						+ " by accessing the link below. \n You will be contacted soon.\n"
						+ "Thank you.\n Payment link: http://localhost:8089/clubmembershipfrontend/paymentmodule/userdata.html?id="
						+ id + "&fee=" + 1000 + "&type=" + "entry"
						+ "&firstName=" + firstName + "&lastName=" + lastName
						+ "&email=" + emailID + "&mobile=" + mobile
						+ "&Occupation=" + occupation);

		SimpleMailMessage simpleMailMessageObj = new SimpleMailMessage();
		simpleMailMessageObj.setFrom(userEmail.getFromAddress());
		simpleMailMessageObj.setTo(userEmail.getToAddress());
		simpleMailMessageObj.setSubject(userEmail.getSubject());
		simpleMailMessageObj.setText(userEmail.getBody());

		try {
			mailSender.send(simpleMailMessageObj);

			/*
			 * user.setStatus(1);
			 * 
			 * registrationRepository.save(user);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";

		}
		return "success";

	}

	public UserRegistration payBill(String id, String type) {

		UserRegistration user = registrationRepository.findOne(id);

		if (type.equals("entry")) {
			if (user.getStatus() == 1) {
				user.setPaymentDone(1);

				UserEmail email = new UserEmail();

				email.setFromAddress("anand.pune38@gmail.com");
				email.setToAddress(user.getEmailId());
				email.setSubject("Club Membership: Login credentials.");
				email.setBody("Dear"
						+ user.getFirstName()
						+ "\n You have been successfully registered with our club."
						+ "\nYour login credentials are: \n Username: "
						+ user.getEmailId() + "Password: " + user.getPassword()
						+ "\n" + "Thank you. Looking forward to you visit.");

				user.setStatus(2); //Active member & paid 1000 rupees
				registrationRepository.save(user); 

				SimpleMailMessage simpleMailMessageObj = new SimpleMailMessage();
				simpleMailMessageObj.setFrom(email.getFromAddress());
				simpleMailMessageObj.setTo(email.getToAddress());
				simpleMailMessageObj.setSubject(email.getSubject());
				simpleMailMessageObj.setText(email.getBody());

				mailSender.send(simpleMailMessageObj);

				return user;
			} else {
				user.setPaymentDone(1);
				return user;
			}

		} 
		else {
			user.setUserType("permanent");
			user.setStatus(5); //Staff has approved permanent membership request & he has made its payment. 
			registrationRepository.save(user);
			return user;
		}
	}

	public String applyForPermanentMembership(String id) {
		UserRegistration user = registrationRepository.findOne(id);
		if(user == null) {
			return "{ \"result\":\" Error: Invalid user id\" }";
		}
		if(user.getStatus() != 2) {
			return "{ \"result\":\" Error: User is not active\" }"; 
		}
		else {
			user.setStatus(3);
			registrationRepository.save(user);
			return "{ \"result\":\"User " + user.getId() + " successfully applied for permanent membership\" }";
		}
	}

	public String approvePermanentMembershipRequest(String id) {
		UserRegistration user = registrationRepository.findOne(id);
		if(user.getStatus() != 3) {
			return "{ \"result\":\" Error: User has not applied for permanent membership\" }"; 
		}
		else {
			user.setStatus(4);
			registrationRepository.save(user);
			return "{ \"result\":\"Approved User " + user.getId() + " request for permanent membership\" }";
		}
	}
	
	public String saveFacility(UserRegistration userRegistration, String id,
			String type) throws JsonProcessingException, InterruptedException {
		UserRegistration user = null;
		user = registrationRepository.findOne(id);
		ObjectMapper objectMapper = new ObjectMapper();
		if (type.equals("temporary")) {
			if (user != null) {
				user.setFacilities(userRegistration.getFacilities());
				registrationRepository.save(user);
				String registerJson = objectMapper.writeValueAsString(user);
				return registerJson;
			} else {
				return null;
			}
		} else {
			user = registrationRepository.findOne(id);
		
			List<Facilities> list = user.getFacilities();
			
			if (list == null) {
			
				user.setFacilities(userRegistration.getFacilities());
				registrationRepository.save(user);
			} else {
				
				list.addAll(userRegistration.getFacilities());
				user.setFacilities(list);
				registrationRepository.save(user);
			}
			String registerJson = objectMapper.writeValueAsString(user);
			return registerJson;
		}
	}

	public List<Facilities> getBillsByUser(String id) {

		UserRegistration user = registrationRepository.findOne(id);
		List<Facilities> facilityList = user.getFacilities();
		if(facilityList == null || facilityList.size() == 0) {
			facilityList = new ArrayList<Facilities>();
		}
		if (user.getUserType().equals("permanent")) {

			Facilities f = new Facilities("Sports Club", "Membership Renewal",
					20000);

			facilityList.add(f);

		}

		return facilityList;
	}

	public HashMap<String, String> renewal(String id) {

		HashMap<String, String> response = new HashMap<String, String>();

		if (isPermanent(id)) {
			response.put("status", "success");
			return response;
		}
		response.put("status", "failure");
		return response;

	}

	private boolean isPermanent(String id) {

		UserRegistration user = registrationRepository.findOne(id);
		Date currentDate = new Date();
		Date registeredDate = user.getRegisteredDate();
		long currentMinutes = currentDate.getTime();

		user.setPreviousRenewalTime(currentMinutes);

		long registeredMinutes = registeredDate.getTime();
		if (currentMinutes - registeredMinutes > 180000) {

			user.setRenewal(1);
			registrationRepository.save(user);
			return true;
		}

		return false;
	}

	// view list of all active users
	public List<UserRegistration> viewActiveUserList() {

		Query query = new Query();
		query.addCriteria(Criteria.where("status").is("1"));

		return registrationRepository.findByStatus(1);

	}

	public boolean checkUsersList(String emailId) {

		emailId = emailId + ".com";
		List<UserRegistration> user = registrationRepository
				.findByEmailId(emailId);

		if (user.size() == 0) {

			return false;
		}

		else
			return true;

	}

	public String updateAddOns(UserRegistration userRegistration, String id)
			throws JsonProcessingException {

		UserRegistration user = new UserRegistration();

		user = registrationRepository.findOne(id);
		ObjectMapper objectMapper = new ObjectMapper();

		if (user.getUserType().equals("permanent")) {

			user.setAddOns(userRegistration.getAddOns());
			registrationRepository.save(user);

			String registerJson = objectMapper.writeValueAsString(user);
			return registerJson;

		}
		return null;

	}

	public Set<AddOns> viewAddOnsDetails(String id) {

		UserRegistration user = registrationRepository.findOne(id);

		Set<AddOns> addOnsList = user.getAddOns();

		return addOnsList;
	}

	public HashMap<String, String> checkRenewal(String id) {

		HashMap<String, String> response = new HashMap<String, String>();

		UserRegistration user = registrationRepository.findOne(id);

		long previousRenewalTime = user.getPreviousRenewalTime();

		Date currentDate = new Date();
		long currentMinutes = currentDate.getTime();

	

		if ((currentMinutes - previousRenewalTime) > 180000) {

			user.setRenewal(2);

			user.setPreviousRenewalTime(currentMinutes);

			registrationRepository.save(user);

			response.put("status", "success");
			return response;

		}

		response.put("status", "failure");
		return response;
	}

	public HashMap<String, String> request(String email, String status) {
		HashMap<String, String> response = new HashMap<String, String>();
		String result = "";
		if (status.equals("accept")) {

			result = acceptRequest(email);
			response.put("status", result);
			response.put("message", "Request Accepted Successfully");
		} else {
			rejectRequest(email);
			response.put("status", result);
			response.put("message", "Request Rejected Successfully");
		}
		return response;
	}

	public List<UserRegistration> getUsersByStatus(String strStatusCode) {
		/* statusCode description:
		 	0: Temporary user
			1: Active user
			2: Paid 1000 rupees
			3: Applied for permanent user
			4: Approved for permanent request
			5: Made payment for permanent membership
		 */
		if(strStatusCode == null) {
			return null;
		}
		int statusCode = Integer.parseInt(strStatusCode);
		if (statusCode < 0 || statusCode > 5) {  //Invalid status code
			return null;
		}
		return registrationRepository.findByStatus(statusCode);
	}
}
