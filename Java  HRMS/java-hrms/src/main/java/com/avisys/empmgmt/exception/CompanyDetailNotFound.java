package com.avisys.empmgmt.exception;

public class CompanyDetailNotFound extends RuntimeException{
	
	private String message;
	
	public CompanyDetailNotFound() {
		
	}

	public CompanyDetailNotFound(String message) {
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
