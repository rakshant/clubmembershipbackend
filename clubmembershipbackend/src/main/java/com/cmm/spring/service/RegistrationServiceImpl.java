package com.cmm.spring.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

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

	public String register(UserRegistration userRegistration)
			throws JsonProcessingException {

		int flag = 0;
		ObjectMapper mapper = new ObjectMapper();

		Query query = new Query();

		query.addCriteria(Criteria.where("emailId").regex(
				userRegistration.getEmailId()));

		registrationCheckList = mongoOperation.find(query,
				UserRegistration.class);

		if (registrationCheckList.size() != 0) {
			flag = 1;

			return "failed";
		}

		if (flag == 0) {

			Date currentDate = new Date();
			Date enteredDate = userRegistration.getDateOfBirth();

			int currentYear = currentDate.getYear();
			int enteredYear = enteredDate.getYear();

			System.out.println(enteredYear - currentYear);

			if (currentDate.compareTo(enteredDate) != -1) {

				if (currentYear - enteredYear > 16) {

					UserRegistration user = registrationRepository
							.insert(userRegistration);
					String registerJson = mapper.writeValueAsString(user);
					return "success";
				}
			}
		}

		return "failed";
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

	public List<HostingCount> aggregationOfType(){
		
		  List<HostingCount> hostingCountList=new ArrayList<HostingCount>();
	        HostingCount hostingCountObj=new HostingCount();
	        List<DBObject> dbList=new ArrayList<DBObject>();

		try{
			MongoClient mongoClient = new MongoClient();
			DB db = mongoClient.getDB("test");
			DBCollection ledger = db.getCollection("user_registrations");
			DBObject unwind=new BasicDBObject("$unwind","$facilities");
			dbList.add(unwind);
			DBObject projectFields = new BasicDBObject("Total_Fee", "$facilities.price");
			projectFields.put("Category", "$facilities.category");
	        projectFields.put("Name", "$facilities.type");
	        DBObject project = new BasicDBObject("$project", projectFields );
	        dbList.add(project);
	        DBObject groupFields = new BasicDBObject( "_id", "$Name");
	        groupFields.put("TotalFee", new BasicDBObject( "$sum", "$Total_Fee"));
	        DBObject group = new BasicDBObject("$group", groupFields);
	        dbList.add(group);		//This is adding parameters in DbObjectList
	      
	        AggregationOutput output = ledger.aggregate(dbList);
	      
	        Iterable<DBObject> iterable = output.results();
	        Iterator<DBObject> iterator=iterable.iterator();
	        while(iterator.hasNext()) 
	        {
	        	DBObject dbobj=iterator.next();	        	
	        
	        	hostingCountObj=mongoOperation.getConverter().read(HostingCount.class, dbobj);
	        	
	        	hostingCountList.add(hostingCountObj);	//This is adding parameters in HostingCountList
	        }
	        return hostingCountList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public UserRegistration addFile(String id, UserRegistration file)
			throws IOException {
		UserRegistration user = new UserRegistration();
		user = registrationRepository.findOne(id);

		if (user != null) {
			user.setBytes(file.getBytes());
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
		System.out.println("message received to reject request-" + email);
		UserRegistration user = registrationRepository.findByEmailId(email)
				.get(0);
		registrationRepository.delete(user);
	}

	public String acceptRequest(String email) {

		UserRegistration user = registrationRepository.findByEmailId(email)
				.get(0);
		String id = user.getId();

		System.out.println("message received to accept request" + email);
		UserEmail userEmail = new UserEmail();
		userEmail.setFromAddress("clubmembershipuser@gmail.com");
		userEmail.setToAddress(email);
		userEmail.setSubject("ClubMembership: Entrance fee amount payment");
		userEmail
				.setBody("Please pay the Entrance fee amount of Rs. "
						+ 1000
						+ " by accessing the link below. \n You will be contacted soon.\n"
						+ "Thank you.\n Payment link: http://localhost:8089/clubmembershipfrontend/paymentmodule/paymentModule.html?id="
						+ id + "&fee=" + 1000 + "&type=" + "entry");

		SimpleMailMessage simpleMailMessageObj = new SimpleMailMessage();
		simpleMailMessageObj.setFrom(userEmail.getFromAddress());
		simpleMailMessageObj.setTo(userEmail.getToAddress());
		simpleMailMessageObj.setSubject(userEmail.getSubject());
		simpleMailMessageObj.setText(userEmail.getBody());

		try {
			mailSender.send(simpleMailMessageObj);

			System.out.println(user.getEmailId());
			user.setStatus(1);
			System.out.println(user.getId());
			registrationRepository.save(user);

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";

		}
		return "success";

	}

	public UserRegistration payBill(String id,String type) {
		
		UserRegistration user = registrationRepository.findOne(id);
		
		if(type.equals("entry")){
			

			if (user.getStatus() == 1) {
				user.setPaymentDone(1);

				UserEmail email = new UserEmail();

				email.setFromAddress("clubmembershipuser@gmail.com");
				email.setToAddress(user.getEmailId());
				email.setSubject("Club Membership: Login credentials.");
				email.setBody("Dear" + user.getFirstName()
						+ "\n You have been successfully registered with our club."
						+ "\nYour login credentials are: \n Username: "
						+ user.getEmailId() + "Password: " + user.getPassword()
						+ "\n" + "Thank you. Looking forward to you visit.");

				registrationRepository.save(user);

				SimpleMailMessage simpleMailMessageObj = new SimpleMailMessage();
				simpleMailMessageObj.setFrom(email.getFromAddress());
				simpleMailMessageObj.setTo(email.getToAddress());
				simpleMailMessageObj.setSubject(email.getSubject());
				simpleMailMessageObj.setText(email.getBody());

				mailSender.send(simpleMailMessageObj);

				return user;
			}
			else{
				user.setPaymentDone(1);
				return user;
			}
			
		}
		else{
			user.setUserType("permanent");
			return user;
		}

		

	}

	public String saveFacility(UserRegistration userRegistration, String id)
			throws JsonProcessingException {
		UserRegistration user = new UserRegistration();

		user = registrationRepository.findOne(id);
		ObjectMapper objectMapper = new ObjectMapper();

		if (user != null) {

			user.setFacilities(userRegistration.getFacilities());
			UserRegistration user1 = registrationRepository.save(user);
			String registerJson = objectMapper.writeValueAsString(user);
			return registerJson;
		} else {
			return null;
		}

	}

	public List<Facilities> getBillsByUser(String id) {

		UserRegistration user = registrationRepository.findOne(id);
		List<Facilities> facilityList = user.getFacilities();
		return facilityList;
	}

	public boolean renewal(String id) {

		System.out.println("int method renewal");
		UserRegistration user = registrationRepository.findOne(id);
		if (isPermanent(id)) {
			// user.setUserType("permanent");
			// registrationRepository.save(user);
			return true;
		}
		return false;

	}

	private boolean isPermanent(String id) {

		System.out.println("int method isPermanent");

		UserRegistration user = registrationRepository.findOne(id);

		Date currentDate = new Date();

		System.out.println("date: " + currentDate);

		System.out.println("registered date: " + user.getRegisteredDate());

		System.out.println("this time: " + currentDate.getTime());

		Date registeredDate = user.getRegisteredDate();

		long currentMinutes = currentDate.getTime();
		System.out.println("********current min: " + currentMinutes);

		long registeredMinutes = registeredDate.getTime();
		System.out.println("********reg min: " + registeredMinutes);

		System.out.println(currentMinutes - registeredMinutes);

		if (currentMinutes - registeredMinutes > 180000) {
			return true;
		}

		return false;
	}
}
