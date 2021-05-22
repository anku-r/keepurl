package com.ankur.keepurl.app.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@ControllerAdvice
public class KeepUrlServiceExceptionHandler {
	
	@ExceptionHandler(KeepUrlServiceException.class)
	public ResponseEntity<Error> handleException(KeepUrlServiceException ex) {
		
	    return exceptionResponse(ex.getStatus(), ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Error> handleException(MethodArgumentNotValidException ex) {
		
		FieldError fieldError = ex.getBindingResult().getFieldError();
		String validationErrorMessage = "Validation failed on " 
						+ fieldError.getField() + ", " + fieldError.getDefaultMessage();
		return exceptionResponse(HttpStatus.BAD_REQUEST, validationErrorMessage);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Error> handleException(MethodArgumentTypeMismatchException ex) {
		
		return exceptionResponse(HttpStatus.BAD_REQUEST, "Invalid data in parameter");
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Error> handleException(HttpMessageNotReadableException ex) {
		
		return exceptionResponse(HttpStatus.BAD_REQUEST, "HTTP request/body is invalid");
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Error> handleException(AccessDeniedException ex) {
		
		return exceptionResponse(HttpStatus.FORBIDDEN, "You are unauthorized to access the content or perform this action");
	}
	
	@ExceptionHandler(Exception.class)
	public  ResponseEntity<Error> handleException(Exception ex, Model model) {
		
		ex.printStackTrace();
		return exceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
	
	private ResponseEntity<Error> exceptionResponse(HttpStatus status, String message) {
		
		Error error = new Error();
		error.setStatus(status);
		error.setMessage(message);
		error.setDateTime(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(new Date()));
		return new ResponseEntity<Error>(error, status);
	}
}

@Data
class Error {
	
	private HttpStatus status;
	private String message;
	
	@JsonProperty("datetime")
	private String dateTime;
}