package com.banking.project.exception.notfound;

public class TransactionNotFoundException extends InputNotFoundException {
    public TransactionNotFoundException(final String message) {
        super(message);
    }
}
