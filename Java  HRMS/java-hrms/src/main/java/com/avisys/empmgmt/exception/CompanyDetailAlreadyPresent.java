package com.avisys.empmgmt.exception;

public class CompanyDetailAlreadyPresent extends RuntimeException {

	private String message;
	
	public CompanyDetailAlreadyPresent() {
		
	}

	public CompanyDetailAlreadyPresent(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
