package com.synchrony.rest.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message, Throwable exception) {
		super(message, exception);
	}
}
