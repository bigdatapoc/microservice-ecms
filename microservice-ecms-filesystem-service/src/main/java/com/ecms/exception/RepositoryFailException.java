package com.ecms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "FFmpeg command failed to execute message.")
public class RepositoryFailException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -189676985683024265L;

	public RepositoryFailException(String message) {

		super(message);	
	}

}
