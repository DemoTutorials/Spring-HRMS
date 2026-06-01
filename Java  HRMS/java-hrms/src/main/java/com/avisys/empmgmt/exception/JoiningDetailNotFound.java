package com.avisys.empmgmt.exception;

public class JoiningDetailNotFound extends RuntimeException{

	private String message;
	
	public JoiningDetailNotFound() {
		// TODO Auto-generated constructor stub
	}
	

	public JoiningDetailNotFound(String message) {
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
