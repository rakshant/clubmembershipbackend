package com.cmm.spring.entity;

import java.util.HashMap;

public class Fees {
	HashMap<String, Integer> fee=new HashMap<String, Integer>();
	public Fees() {
		// TODO Auto-generated constructor stub
	}
	public Fees(HashMap<String, Integer> fee) {
		super();
		this.fee = fee;
	}
	public HashMap<String, Integer> getFee() {
		return fee;
	}
	public void setFee(HashMap<String, Integer> fee) {
		this.fee = fee;
	}
	
}
