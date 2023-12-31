package com.banking.project.exception.exists;

public class UserAlreadyExistsException extends InputAlreadyExistsException {
    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
