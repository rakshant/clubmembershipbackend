package com.cmm.spring.entity;

public class Facilities {

	private String category;
	private String type;
	private int price;

	public Facilities() {
	}

	public Facilities(String category, String type, int price) {
		super();
		this.category = category;
		this.type = type;
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
