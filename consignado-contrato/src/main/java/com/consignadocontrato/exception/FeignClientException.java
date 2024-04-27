package com.consignadocontrato.exception;

import com.consignadocontrato.util.ErrorResponse;

public class FeignClientException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ErrorResponse errorResponse;

    public FeignClientException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}