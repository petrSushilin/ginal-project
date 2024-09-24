package ru.sberstart.finalproject.global.exceptions;

public class NotAvailableActionsException extends RuntimeException {
    public NotAvailableActionsException(String message) {
        super(message);
    }

    public NotAvailableActionsException() {
    }
}
