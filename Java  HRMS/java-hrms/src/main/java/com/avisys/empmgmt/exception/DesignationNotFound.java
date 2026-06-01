package com.avisys.empmgmt.exception;

public class DesignationNotFound extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;
	
	public DesignationNotFound() {
		// TODO Auto-generated constructor stub
	}
	

	public DesignationNotFound(String message) {
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
