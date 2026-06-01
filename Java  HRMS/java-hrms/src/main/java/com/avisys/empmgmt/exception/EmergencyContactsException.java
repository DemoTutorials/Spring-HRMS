package com.avisys.empmgmt.exception;

public class EmergencyContactsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public EmergencyContactsException() {
		// TODO Auto-generated constructor stub
	}
	

	public EmergencyContactsException(String message) {
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
