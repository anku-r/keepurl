package com.ankur.keepurl.app.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class UnauthorizedAccessException extends KeepUrlServiceException {

    public UnauthorizedAccessException() {
	super(HttpStatus.FORBIDDEN, "Request is Unauthorized");
    }

    public UnauthorizedAccessException(String message) {
	super(HttpStatus.FORBIDDEN, message);
    }
}
