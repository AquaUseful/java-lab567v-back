package org.psu.lab567.exception;

public class UserExistsException extends RegisterException {
    private static final String msg = "Такой пользователь уже существует";

    public UserExistsException() {
        super(msg);
    }
}
