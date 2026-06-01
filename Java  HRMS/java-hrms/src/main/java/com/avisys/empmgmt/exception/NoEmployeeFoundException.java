package com.avisys.empmgmt.exception;

public class NoEmployeeFoundException extends RuntimeException {


	private String message;
	
	public NoEmployeeFoundException() {
	
	}
	

	public NoEmployeeFoundException(String message) {
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
