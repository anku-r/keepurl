package com.ankur.keepurl.app.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class UrlDetailAlreadyExistException extends KeepUrlServiceException {

    public UrlDetailAlreadyExistException(String message) {
	super(HttpStatus.CONFLICT, message);
    }

    public UrlDetailAlreadyExistException() {
	super(HttpStatus.CONFLICT, "URL Details with provided title/url is already present");
    }
}
