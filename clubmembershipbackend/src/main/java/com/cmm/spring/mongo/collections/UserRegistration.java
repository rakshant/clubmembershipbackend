package com.cmm.spring.mongo.collections;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.cmm.spring.entity.Facilities;

@Document(collection = "user_registrations")
public class UserRegistration {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	private String firstName;
	private String lastName;
	private String emailId;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date dateOfBirth;

	private Long mobileNumber;
	private String occupation;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date registeredDate;

	private String password;


	private int status=0;

	private String userType;

	
	private int entranceFee=1000;
	
	private int paymentDone=0;
	
	@Autowired
	Facilities facilities;
	
	public UserRegistration() {}
	
	
	
	public UserRegistration(String firstName, String lastName,
			String emailId, Date dateOfBirth, Long mobileNumber,
			String occupation,Date registeredDate,String password,int status,String userType) {
		


		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.occupation = occupation;

		this.registeredDate=new Date();
		this.password= ""+((int)(Math.random()*9000)+1000);
		this.status=status;
		this.userType=userType;


	}
	
	//this constructor is for update service
		public UserRegistration(long mobileNumber,String occupation,String password) 
		{
			
			this.mobileNumber = mobileNumber;
			this.occupation = occupation;
			this.password=password;
		}


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
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}





	@Override
	public String toString() {
		return "UserRegistration [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", emailId=" + emailId
				+ ", dateOfBirth=" + dateOfBirth + ", mobileNumber="
				+ mobileNumber + ", occupation=" + occupation + ", status="
				+ status + ", userType=" + userType + ", facilities="
				+ facilities + "]";
	}

	public int getEntranceFee() {
		return entranceFee;
	}

	public void setEntranceFee(int entranceFee) {
		this.entranceFee = entranceFee;
	}

	public Facilities getFacilities() {
		return facilities;
	}

	public void setFacilities(Facilities facilities) {
		this.facilities = facilities;
	}

	public int getPaymentDone() {
		return paymentDone;
	}

	public void setPaymentDone(int paymentDone) {
		this.paymentDone = paymentDone;
	}

	
	

}
