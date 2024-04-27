package com.consignadocontrato.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.consignadocontrato.exception.DataInconsistencyException;
import com.consignadocontrato.exception.DataIntegrityViolationException;
import com.consignadocontrato.exception.ResourceNotFoundException;
import com.consignadocontrato.util.ErrorResponse;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
    	return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    	
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                String errorMessage = fieldError.getField() + ": " + fieldError.getDefaultMessage();
                errors.add(errorMessage);
            } else {
                errors.add(error.getDefaultMessage());
            }
        });

        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Falha na validação: "+ errors, System.currentTimeMillis());
    	
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    	
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erro de violação de integridade de dados : " + ex.getMessage(), System.currentTimeMillis());

    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleHttpMessageNotReadableException(MismatchedInputException ex) {
    	
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Requisição inválida : " + ex.getMessage(), System.currentTimeMillis());

    }
    
    @ExceptionHandler(DataInconsistencyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleDataInconsistencyException(DataInconsistencyException ex) {
    	
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erro inconsistencia de dados : " + ex.getMessage(), System.currentTimeMillis());

    }
}