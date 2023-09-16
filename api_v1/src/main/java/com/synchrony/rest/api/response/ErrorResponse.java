package com.synchrony.rest.api.response;

import java.util.Date;

public class ErrorResponse {
	
	private Date timestamp;
	private String message;
	private String details;

	public ErrorResponse(Date timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	  }
	
	public String createJson() {
		return "{\"timestamp\" : \"" + this.timestamp + "\"," 
				+ "\"error\" : \"" + this.message + "\"," 
				+ "\"reason\" : \"" +this.details + "\"}";
	}
}