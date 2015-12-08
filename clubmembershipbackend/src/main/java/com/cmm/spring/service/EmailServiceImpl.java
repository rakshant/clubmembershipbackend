package com.cmm.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cmm.spring.mongo.collections.UserEmail;
import com.cmm.spring.mongo.collections.UserLogin;
import com.cmm.spring.mongo.collections.UserRegistration;
import com.cmm.spring.rest.repository.EmailRepository;
import com.cmm.spring.rest.repository.LoginRepository;
import com.cmm.spring.rest.repository.RegistrationRepository;







import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;



@Service
public class EmailServiceImpl implements EmailService {
	
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private MailSender mailSender; 
	
	
	public void sendEmail(UserEmail email)
	{			
		
		//email.setToAddress(registrationRepository.);
		
		String toAddress=email.getToAddress();
		String fromAddress=email.getFromAddress();
		String subject=email.getSubject();
		String msgBody=email.getBody();
		
	
		SimpleMailMessage simpleMailMessageObj = new SimpleMailMessage();
		simpleMailMessageObj.setFrom(fromAddress);
		simpleMailMessageObj.setTo(toAddress);
		simpleMailMessageObj.setSubject(subject);
		simpleMailMessageObj.setText(msgBody);
		
		System.out.println("mail: "+toAddress);
		System.out.println(simpleMailMessageObj);
		
		
		try{
		mailSender.send(simpleMailMessageObj);
		}
		catch(Exception e){
			//System.out.println(e);
			e.printStackTrace();
		}
	}


	

/*	public void sendEmail(UserRegistration userRegistration) {
		
		
		
		SimpleMailMessage simpleMailMessageObj = new SimpleMailMessage();
		simpleMailMessageObj.setFrom(fromAddress);
		simpleMailMessageObj.setTo(toAddress);
		simpleMailMessageObj.setSubject(subject);
		simpleMailMessageObj.setText(msgBody);
		mailSender.send(simpleMailMessageObj);
		
		
		registrationRepository.save(userRegistration);
	}*/

	/*public UserRegistration read(String emailId) {
		return emailRepository.findOne(emailId);
	}
*/

}
