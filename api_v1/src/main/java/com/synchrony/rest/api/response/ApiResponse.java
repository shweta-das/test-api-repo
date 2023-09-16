package com.synchrony.rest.api.response;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.synchrony.rest.api.model.User;

import okhttp3.Response;

public class ApiResponse {

	public static ResponseEntity<ServiceResponse> getResponse(User result) {
		ServiceResponse response = new ServiceResponse();
		response.result = ResponseEntity.ok().body(result);
	    return ResponseEntity.ok().body(response);	
	}
	
	public static ResponseEntity<ServiceResponse> getResponse(String result) {
		ServiceResponse response = new ServiceResponse();
		response.result = ResponseEntity.ok().body(result);
	    return ResponseEntity.ok().body(response);	
	}
	
	public static ResponseEntity<ServiceResponse> getResponse(List<User> result) {
		ServiceResponse response = new ServiceResponse();
		response.result = ResponseEntity.ok().body(result);
	    return ResponseEntity.ok().body(response);	
	}

	public static ResponseEntity<ServiceResponse> getResponse(Response result) {
		ServiceResponse response = new ServiceResponse();
		response.result = ResponseEntity.ok().body(result);
	    return ResponseEntity.ok().body(response);	
	}
}
