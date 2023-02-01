package org.psu.lab567.exception;

public class UserExistsException extends RegisterException {
    private static final String msg = "Такой email уже зарегистрирован";

    public UserExistsException() {
        super(msg);
    }
}
