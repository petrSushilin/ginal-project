package ru.sberstart.finalproject.global.exceptions;

public class ForbiddenTransactionException extends RuntimeException {
    public ForbiddenTransactionException(String message) {
        super(message);
    }

    public ForbiddenTransactionException() {
    }
}
