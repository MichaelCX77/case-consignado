package com.consignadocontrato.exception;

import org.springframework.http.HttpStatus;

public class DataInconsistencyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;
	
	public DataInconsistencyException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
	
}
