package com.banking.project.exception.notfound;

public class UserNotFoundException extends InputNotFoundException {
    public UserNotFoundException(final String message) {
        super(message);
    }
}
