package com.kairosds.tweetsreader.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final String NOT_FOUND_RESPONSE = "{\"response\":\"resource not found\"}";

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, NOT_FOUND_RESPONSE, headers, HttpStatus.NOT_FOUND, request);
    }

}
