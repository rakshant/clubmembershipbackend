package com.cmm.spring.entity;


import java.util.Date;

import com.mongodb.BasicDBObject;

public class Registration {
	
	private String firstName;
	private String lastName;
	private String emailId;
	private Date dateOfBirth;
	private Long mobileNumber;
	private String occupation;
	
	private Date registeredDate;
	private String password;
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		
		this.registeredDate = registeredDate;
	}
	
	

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Registration [firstName=" + firstName + ", lastname="
				+ lastName + ", emailId=" + emailId + ", dateOfBirth="
				+ dateOfBirth + ", mobileNumber=" + mobileNumber
				+ ", occupation=" + occupation + "]";
	}
	
	
	
	
	
	
	
	
	

}
