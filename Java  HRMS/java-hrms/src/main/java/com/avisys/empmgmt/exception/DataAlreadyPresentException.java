
package com.avisys.empmgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DataAlreadyPresentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
