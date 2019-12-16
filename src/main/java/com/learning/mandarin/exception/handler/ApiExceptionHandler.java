package com.learning.mandarin.exception.handler;

import com.learning.mandarin.exception.DataInvalidException;
import com.learning.mandarin.exception.ResourceNotFoundException;
import com.learning.mandarin.exception.ServiceUnavailableException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { ServiceUnavailableException.class})
    protected ResponseEntity<Object> handleServiceUnavailableException(ServiceUnavailableException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler(value = { DataInvalidException.class})
    protected ResponseEntity<Object> handleDataInvalidException(DataInvalidException ex, WebRequest request)  {
        return handleExceptionInternal(ex, ex, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}


