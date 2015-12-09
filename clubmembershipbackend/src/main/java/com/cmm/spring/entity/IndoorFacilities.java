package com.cmm.spring.entity;

public class IndoorFacilities {
	
	private int tableTennisFees=1000;
	private int badmintonFees=500;
	private int billiardsFees=1500;
	private int healthClubFees=300;
	private int squashFees=2500;
	
	
	public IndoorFacilities(){};
	
	public int getTableTennisFees() {
		return tableTennisFees;
	}
	public void setTableTennisFees(int tableTennisFees) {
		this.tableTennisFees = tableTennisFees;
	}
	public int getBadmintonFees() {
		return badmintonFees;
	}
	public void setBadmintonFees(int badmintonFees) {
		this.badmintonFees = badmintonFees;
	}
	public int getBilliardsFees() {
		return billiardsFees;
	}
	public void setBilliardsFees(int billiardsFees) {
		this.billiardsFees = billiardsFees;
	}
	public int getHealthClubFees() {
		return healthClubFees;
	}
	public void setHealthClubFees(int healthClubFees) {
		this.healthClubFees = healthClubFees;
	}
	public int getSquashFees() {
		return squashFees;
	}
	public void setSquashFees(int squashFees) {
		this.squashFees = squashFees;
	}
	
	
	@Override
	public String toString() {
		return "IndoorFacilities [tableTennisFees=" + tableTennisFees
				+ ", badmintonFees=" + badmintonFees + ", billiardsFees="
				+ billiardsFees + ", healthClubFees=" + healthClubFees
				+ ", squashFees=" + squashFees + "]";
	}
	
	
	

}


