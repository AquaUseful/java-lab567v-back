package org.psu.lab567.exception;

public class WrongPasswordException extends LoginException {

    private static final String msg = "Неверный пароль";

    public WrongPasswordException() {
        super(msg);
    }

}
