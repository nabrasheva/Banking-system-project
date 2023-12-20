package com.banking.project.exception.notfound;

public class AccountNotFoundException extends InputNotFoundException {
    public AccountNotFoundException(final String message) {
        super(message);
    }
}
