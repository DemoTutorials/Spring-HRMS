package com.avisys.empmgmt.exception;

public class AddressException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	        
	        private String message;
	    	
	    	public AddressException() {
	    		
	    	}
	    	

	    	public AddressException(String message) {
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
