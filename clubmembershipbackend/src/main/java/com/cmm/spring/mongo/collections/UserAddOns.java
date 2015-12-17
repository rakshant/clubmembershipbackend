package com.cmm.spring.mongo.collections;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Document(collection="user_addons")
public class UserAddOns {
	
	
	
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private String name;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date dob;
	
	private String relation;
	
	
	
	public UserAddOns(String name, Date dob, String relation) {
		super();
		this.name = name;
		this.dob = dob;
		this.relation = relation;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	@Override
	public String toString() {
		return "AddOns [name=" + name + ", dob=" + dob + ", relation="
				+ relation + "]";
	}
	
	
	
	

}
