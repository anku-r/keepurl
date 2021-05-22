package com.ankur.keepurl.app.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class KeepUrlServiceException extends RuntimeException {
	
	private HttpStatus status; 
	
	private String message;
	
	public KeepUrlServiceException(String message) {		
		super(message);
		this.status = HttpStatus.BAD_REQUEST;
		this.message = message;
	}

}
