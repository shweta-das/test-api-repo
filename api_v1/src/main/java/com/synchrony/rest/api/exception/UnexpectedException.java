package com.synchrony.rest.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class UnexpectedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnexpectedException(String message) {
		super(message);
	}
	
	public UnexpectedException(String message, Throwable exception) {
		super(message, exception);
	}
}
