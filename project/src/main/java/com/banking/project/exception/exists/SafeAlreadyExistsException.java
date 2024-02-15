package com.banking.project.exception.exists;

public class SafeAlreadyExistsException extends InputAlreadyExistsException {
    public SafeAlreadyExistsException(final String message) {
        super(message);
    }
}
