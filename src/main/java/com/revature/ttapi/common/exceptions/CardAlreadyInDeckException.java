package com.revature.ttapi.common.exceptions;

public class CardAlreadyInDeckException extends RuntimeException{
    public CardAlreadyInDeckException() {
        super();
    }

    public CardAlreadyInDeckException(String message) {
        super(message);
    }
}
