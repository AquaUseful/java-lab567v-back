package org.psu.lab567.exception;

public class UserNotFoundException extends LoginException {

    private static final String msg = "Такой пользователь не существует";

    public UserNotFoundException() {
        super(msg);
    }
}
