package com.avisys.empmgmt.exception;

public class JoiningDetailAlreadyPresent extends RuntimeException {

	private String message;
	
	public JoiningDetailAlreadyPresent() {
		// TODO Auto-generated constructor stub
	}
	

	public JoiningDetailAlreadyPresent(String message) {
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
