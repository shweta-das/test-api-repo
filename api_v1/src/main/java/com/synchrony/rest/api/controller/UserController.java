package com.synchrony.rest.api.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synchrony.rest.api.model.User;
import com.synchrony.rest.api.response.ApiResponse;
import com.synchrony.rest.api.response.ServiceResponse;
import com.synchrony.rest.api.service.UserService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/health")
	public ResponseEntity<ServiceResponse> hello() {
		return ApiResponse.getResponse("Project is up and Running!!");
	}

	@PostMapping("/register")
	public ResponseEntity<ServiceResponse> registerUser(@RequestBody User user) {
		return ApiResponse.getResponse(this.userService.registerUser(user));	
	}
	
	@GetMapping("/login")
	public ResponseEntity<ServiceResponse> loginUser(@RequestBody User user) {
		return ApiResponse.getResponse(this.userService.loginUser(user));	
	}

	@GetMapping("/users")
	public ResponseEntity<ServiceResponse> getUsers() {
		return ApiResponse.getResponse(this.userService.getUsers());
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable long id) {
		return ApiResponse.getResponse(this.userService.getUser(id));
	}

}
