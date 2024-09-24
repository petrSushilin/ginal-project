package ru.sberstart.finalproject.global.exceptions;

public class UnsuccessfulOperationException extends RuntimeException {
    public UnsuccessfulOperationException(String message) {
        super(message);
    }

    public UnsuccessfulOperationException() {
    }
}
