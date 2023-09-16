package com.synchrony.rest.api.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.synchrony.rest.api.exception.ResourceNotFoundException;
import com.synchrony.rest.api.exception.UnexpectedException;
import com.synchrony.rest.api.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails.createJson(), HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UnexpectedException.class)
	public ResponseEntity<?> handleUnexpectedException(UnexpectedException ex, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails.createJson(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<?> handleAccessErrors(ResourceAccessException  ex, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails.createJson(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class )
	public ResponseEntity<?> handleServerErrors(HttpServletRequest req, Exception e) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), e.getMessage(), req.getPathInfo());
		return new ResponseEntity<>(errorDetails.createJson(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}