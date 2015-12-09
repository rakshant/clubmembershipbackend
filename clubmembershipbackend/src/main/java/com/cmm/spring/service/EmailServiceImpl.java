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



@Service
public class EmailServiceImpl implements EmailService {
	

	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private MailSender mailSender; 
	
	

	@Autowired
	private MongoOperations mongoOperation;
	
	
	List<UserRegistration> userList;
	 
	
	public String sendEmail(UserRegistration user,UserEmail email)
	{		
		
		Query query = new Query();		
		
		query.addCriteria(Criteria.where("status").is(0));
		
		userList=mongoOperation.find(query, UserRegistration.class);
			
			System.out.println(userList.size());
			
			for(UserRegistration u:userList){
							
					email.setFromAddress("clubmembershipuser@gmail.com");
					email.setToAddress(u.getEmailId());
					email.setSubject("ClubMembership: Entrance fee amount payment");
					email.setBody("Please pay the Entrance fee amount of Rs."+u.getEntranceFee()+" by accessing the link below. \n You will be contacted soon.\n"+
							"Thank you.\n Payment link: http://localhost:8080/pay");
					
					
					SimpleMailMessage simpleMailMessageObj = new SimpleMailMessage();
					simpleMailMessageObj.setFrom(email.getFromAddress());
					simpleMailMessageObj.setTo(email.getToAddress());
					simpleMailMessageObj.setSubject(email.getSubject());
					simpleMailMessageObj.setText(email.getBody());
					
					
					
					
					try{
					mailSender.send(simpleMailMessageObj);
					}
					catch(Exception e){
						return "failure";
					}				
			}
			return "success"; 	
	}


}
