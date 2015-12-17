package com.cmm.spring.mongo.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "budget_allocation")
public class UserTreasurerBudget {

	
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	int indoor;
	int outdoor;
	int leisure;
	
	public UserTreasurerBudget(){}
	
	public int getIndoor() {
		return indoor;
	}
	public void setIndoor(int indoor) {
		this.indoor = indoor;
	}
	public int getOutdoor() {
		return outdoor;
	}
	public void setOutdoor(int outdoor) {
		this.outdoor = outdoor;
	}
	public int getLeisure() {
		return leisure;
	}
	public void setLeisure(int leisure) {
		this.leisure = leisure;
	}
	public UserTreasurerBudget(int indoor, int outdoor, int leisure) {
		super();
		this.indoor = indoor;
		this.outdoor = outdoor;
		this.leisure = leisure;
	}
	
	
	
	
	
	
}
