package org.psu.lab567.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.psu.lab567.pojo.ValidationErrorResponse;
import org.psu.lab567.utils.ValidationViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ValidationErrorResponse> methodArgumentNotValidExcetionHandler(
                        MethodArgumentNotValidException exception) {
                List<ValidationViolation> violations = exception.getBindingResult().getFieldErrors().stream()
                                .map(
                                                error -> new ValidationViolation(
                                                                error.getField(),
                                                                error.getDefaultMessage()))
                                .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ValidationErrorResponse(violations));
        }

        @ExceptionHandler(BindException.class)
        public ResponseEntity<ValidationErrorResponse> methodArgumentNotValidExcetionHandler(
                        BindException exception) {
                List<ValidationViolation> violations = exception.getBindingResult().getFieldErrors().stream()
                                .map(
                                                error -> new ValidationViolation(
                                                                error.getField(),
                                                                error.getDefaultMessage()))
                                .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ValidationErrorResponse(violations));
        }
}
