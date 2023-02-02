package org.psu.lab567.controller;

import org.psu.lab567.exception.AuthException;
import org.psu.lab567.pojo.ExceptionResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionResponse> authExceptionHandler(AuthException exception) {
        final ExceptionResponse response = new ExceptionResponse(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}