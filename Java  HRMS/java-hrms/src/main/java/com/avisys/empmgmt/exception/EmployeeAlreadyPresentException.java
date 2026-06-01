package com.avisys.empmgmt.exception;

public class EmployeeAlreadyPresentException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	
	private String message;
	
	public EmployeeAlreadyPresentException() {
		
	}

	public EmployeeAlreadyPresentException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
