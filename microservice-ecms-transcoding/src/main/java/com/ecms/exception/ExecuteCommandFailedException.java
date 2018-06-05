package com.ecms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "FFmpeg command failed to execute message.")
public class ExecuteCommandFailedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8341467235116925837L;

	public ExecuteCommandFailedException(String message) {

		super(message);	
	}

}
