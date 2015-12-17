package com.cmm.spring.entity;

import java.util.Date;

public class AddOns {
	
	private String name;
	private Date dob;
	private String relation;
	
	
	
	
	public AddOns(){}
	
	public AddOns(String name, Date dob, String relation) {
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
