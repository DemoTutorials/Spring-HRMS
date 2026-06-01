package com.avisys.empmgmt.exception;

public class EmployeeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	
	private String message;
	
	public EmployeeException() {
		
	}

	public EmployeeException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
