package com.avisys.empmgmt.exception;


public class CommonMasterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public CommonMasterException() {
		
	}

	public CommonMasterException(String message) {
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
