package com.springJourney.idempotent.exception;

public class IdempotentException extends RuntimeException {
    public IdempotentException(String message) {
        super(message);
    }
}
