package com.consignadocliente.exception;

import org.springframework.http.HttpStatus;

public class IllegalArgumentException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;
	
	public IllegalArgumentException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
    }

	public HttpStatus getHttpStatus() {
	    return httpStatus;
	}
	
}
