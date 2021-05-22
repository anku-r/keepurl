package com.ankur.keepurl.app.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class UsernameNotFoundException extends KeepUrlServiceException {

	public UsernameNotFoundException() {
		super(HttpStatus.UNAUTHORIZED, "User not found");
	}
	
	public UsernameNotFoundException(String message) {
		super(HttpStatus.UNAUTHORIZED, message);
	}
}
