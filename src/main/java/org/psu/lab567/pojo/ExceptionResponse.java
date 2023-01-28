package org.psu.lab567.pojo;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final String message;

    public ExceptionResponse(Exception exception) {
        this.message = exception.getMessage();
    }
}
