package com.cmm.spring.mongo.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="user_email")
public class UserEmail {
	
	

	@Id
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	String toAddress;
	String fromAddress="clubmembershipuser@gmail.com";
	String subject;
	String body;
	
	
	
	
	
	
	public UserEmail(){}
	



	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public UserEmail(String toAddress, String fromAddress, String subject,
			String body) {
		super();
		this.toAddress = toAddress;
		this.fromAddress = fromAddress;
		this.subject = subject;
		this.body = body;
	}

	@Override
	public String toString() {
		return "UserEmail [id=" + id + ", toAddress=" + toAddress
				+ ", fromAddress=" + fromAddress + ", subject=" + subject
				+ ", body=" + body + "]";
	}
	
	
	
	

}
