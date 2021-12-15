package com.revature.ttapi.common.exceptions;

public class AccountExistsException extends RuntimeException {

    public AccountExistsException() {
        super("An account with those credentials already exists");
    }

    public AccountExistsException(String message) {
        super(message);
    }
}
