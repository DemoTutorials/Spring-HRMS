/*
 * 
 */
package com.avisys.empmgmt.exception;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The Class Error_Details.
 */
public class Error_Details {

	/** timestamp details. */
	@Temporal(TemporalType.TIMESTAMP)
	public Date timestampDetails;

	/** message. */
	public String message;

	/** details. */
	public String details;

	/**
	 * Gets the timestamp details.
	 *
	 * @return the timestamp details
	 */
	public Date getTimestampDetails() {
		return timestampDetails;
	}

	/**
	 * Sets the timestamp details.
	 *
	 * @param timestampDetails the new timestamp details
	 */
	public void setTimestampDetails(Date timestampDetails) {
		this.timestampDetails = timestampDetails;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the details.
	 *
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Sets the details.
	 *
	 * @param details the new details
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * Instantiates a new error details.
	 */
	public Error_Details() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new error details.
	 *
	 * @param timestampDetails the timestamp details
	 * @param message          the message
	 * @param details          the details
	 */
	public Error_Details(Date timestampDetails, String message, String details) {
		super();
		this.timestampDetails = timestampDetails;
		this.message = message;
		this.details = details;
	}

	/**
	 * Use toString method.
	 *
	 * @return Prints error details data in console
	 */
	@Override
	public String toString() {
		return "Error_Details [timestampDetails=" + timestampDetails + ", message=" + message + ", details=" + details
				+ "]";
	}
}
