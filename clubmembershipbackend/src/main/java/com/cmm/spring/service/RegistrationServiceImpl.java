package com.cmm.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.cmm.spring.mongo.collections.UserEmail;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.rest.repository.RegistrationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	public String save(UserRegistration userRegistration)
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
		}

		if (flag == 0) {

			UserRegistration userReg = registrationRepository
					.insert(userRegistration);
			String registerJson = mapper.writeValueAsString(userReg);
			return registerJson;
		}

		return null;
	}

	public List<UserRegistration> read(String id) {

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
			// Setting all the old details of that document
			if(userRegistration.getOccupation()!=null)
				user.setOccupation(userRegistration.getOccupation());
			
			if(userRegistration.getMobileNumber()!=0)
				user.setMobileNumber(userRegistration.getMobileNumber());
			
			if(userRegistration.getPassword()!=null)
				user.setPassword(userRegistration.getPassword());
			
			user = registrationRepository.save(user);

			String registerJson = objectMapper.writeValueAsString(user);

			return registerJson;
		} else {
			return null;
		}
	}

	public List<UserRegistration> view(String id) {

		viewDetailsList = registrationRepository.findById(id);

		return viewDetailsList;

	}

	public void rejectRequest(String email) {
		System.out.println("message received to reject request-" + email);

	}

	public String acceptRequest(String email) {
		System.out.println("message received to accept request" + email);
		UserEmail userEmail = new UserEmail();
		userEmail.setFromAddress("clubmembershipuser@gmail.com");
		userEmail.setToAddress(email);
		userEmail.setSubject("ClubMembership: Entrance fee amount payment");
		userEmail
				.setBody("Please pay the Entrance fee amount of Rs. "
						+ 1200
						+ " by accessing the link below. \n You will be contacted soon.\n"
						+ "Thank you.\n Payment link: http://localhost:8080/pay");

		SimpleMailMessage simpleMailMessageObj = new SimpleMailMessage();
		simpleMailMessageObj.setFrom(userEmail.getFromAddress());
		simpleMailMessageObj.setTo(userEmail.getToAddress());
		simpleMailMessageObj.setSubject(userEmail.getSubject());
		simpleMailMessageObj.setText(userEmail.getBody());

		try {
			mailSender.send(simpleMailMessageObj);

			UserRegistration user = registrationRepository.findOne(email);

			user.setStatus(1);

			registrationRepository.save(user);

		} catch (Exception e) {
			return "failure";

		}
		return "success";

	}

	public UserRegistration paymentDone(String id) {

		UserRegistration user = registrationRepository.findOne(id);

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
		return null;

	}

}
