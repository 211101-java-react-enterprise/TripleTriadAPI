package com.revature.ttapi.common.exceptions;

public class CardIDBeyondCollectionTotalException extends RuntimeException {
    public CardIDBeyondCollectionTotalException() {
        super();
    }

    public CardIDBeyondCollectionTotalException(String message) {
        super(message);
    }
}
