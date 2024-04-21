package com.consignadoregister.exception;

import org.springframework.http.HttpStatus;

public class DataIntegrityViolationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;
	
	public DataIntegrityViolationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
