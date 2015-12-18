package com.cmm.spring.entity;

public class TreasurerBudget {

	
	int indoor;
	int outdoor;
	int leisure;
	
	
	public TreasurerBudget(){}
	
	
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
	public TreasurerBudget(int indoor, int outdoor, int leisure) {
		super();
		this.indoor = indoor;
		this.outdoor = outdoor;
		this.leisure = leisure;
	}
	
	
	
	
	
	
}
