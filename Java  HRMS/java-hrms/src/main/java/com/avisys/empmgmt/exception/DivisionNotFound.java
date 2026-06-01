package com.avisys.empmgmt.exception;

public class DivisionNotFound extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DivisionNotFound(String message){
		super(message);
	}
}
