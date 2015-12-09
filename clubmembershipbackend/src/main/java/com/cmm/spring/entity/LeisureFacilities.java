package com.cmm.spring.entity;

public class LeisureFacilities {
	
	
	
	private int cardRoom=500;
	private int library=300;
	private int restaurant=1000;
	private int banquetHall=50000;
	private int conferenceHall=40000;
	
	
	
	public LeisureFacilities(){}
	
	
	public int getCardRoom() {
		return cardRoom;
	}
	public void setCardRoom(int cardRoom) {
		this.cardRoom = cardRoom;
	}
	public int getLibrary() {
		return library;
	}
	public void setLibrary(int library) {
		this.library = library;
	}
	public int getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(int restaurant) {
		this.restaurant = restaurant;
	}
	public int getBanquetHall() {
		return banquetHall;
	}
	public void setBanquetHall(int banquetHall) {
		this.banquetHall = banquetHall;
	}
	public int getConferenceHall() {
		return conferenceHall;
	}
	public void setConferenceHall(int conferenceHall) {
		this.conferenceHall = conferenceHall;
	}
	@Override
	public String toString() {
		return "LeisureFacilities [cardRoom=" + cardRoom + ", library="
				+ library + ", restaurant=" + restaurant + ", banquetHall="
				+ banquetHall + ", conferenceHall=" + conferenceHall + "]";
	}
	
	

}
