package com.cmm.spring.entity;

public class Email {
	
	
	private String toAddress;
	private String fromAddress;
	private String subject;
	private String body;
	
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "Email [toAddress=" + toAddress + ", fromAddress=" + fromAddress
				+ ", subject=" + subject + ", body=" + body + "]";
	}
	


	
	

}
