package com.ankur.keepurl.app.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class RequestNotFoundException extends KeepUrlServiceException {

    public RequestNotFoundException() {
	super(HttpStatus.NOT_FOUND, "Not Found");
    }

    public RequestNotFoundException(String message) {
	super(HttpStatus.NOT_FOUND, message);
    }
}
