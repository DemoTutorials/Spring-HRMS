package com.avisys.empmgmt.exception;

public class EmployeeTypeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	
	private String message;
	
	public EmployeeTypeException() {
		
	}

	public EmployeeTypeException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
