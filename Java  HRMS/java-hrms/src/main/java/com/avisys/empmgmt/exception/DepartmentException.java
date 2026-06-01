package com.avisys.empmgmt.exception;

public class DepartmentException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public DepartmentException() {
		
	}

	public DepartmentException(String message) {
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
