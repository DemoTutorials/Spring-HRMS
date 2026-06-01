package com.avisys.empmgmt.exception;

public class DuplicatePersonalDetail extends RuntimeException {

	private String message;
	
	public DuplicatePersonalDetail() {
		
	}

	public DuplicatePersonalDetail(String message) {
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
