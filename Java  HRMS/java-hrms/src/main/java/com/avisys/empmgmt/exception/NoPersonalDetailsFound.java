package com.avisys.empmgmt.exception;

public class NoPersonalDetailsFound extends RuntimeException{

	private String message;
	
	public NoPersonalDetailsFound() {
		// TODO Auto-generated constructor stub
	}
	

	public NoPersonalDetailsFound(String message) {
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
