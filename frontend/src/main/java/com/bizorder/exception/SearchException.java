package com.bizorder.exception;

import org.springframework.http.HttpStatus;

public class SearchException {
    
    // Message to send to client
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public SearchException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Getter for throwable
    public Throwable getThrowable() {
        return throwable;
    }

    // Getter for httpStatus
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
