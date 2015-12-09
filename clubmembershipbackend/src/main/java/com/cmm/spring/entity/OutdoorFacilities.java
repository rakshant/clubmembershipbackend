package com.cmm.spring.entity;

public class OutdoorFacilities {
	
	
	private int lawnTennis=1500;
	private int swimming=100;
	private int cricket=3000;
	private int playground=300;
	
	
	public OutdoorFacilities(){};
	
	
	public int getLawnTennis() {
		return lawnTennis;
	}
	public void setLawnTennis(int lawnTennis) {
		this.lawnTennis = lawnTennis;
	}
	public int getSwimming() {
		return swimming;
	}
	public void setSwimming(int swimming) {
		this.swimming = swimming;
	}
	public int getCricket() {
		return cricket;
	}
	public void setCricket(int cricket) {
		this.cricket = cricket;
	}
	public int getPlayground() {
		return playground;
	}
	public void setPlayground(int playground) {
		this.playground = playground;
	}
	
	
	@Override
	public String toString() {
		return "OutdoorFacilities [lawnTennis=" + lawnTennis + ", swimming="
				+ swimming + ", cricket=" + cricket + ", playground="
				+ playground + "]";
	}
	
	
		


}
