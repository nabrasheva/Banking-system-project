package com.banking.project.exception.exists;

public class InputAlreadyExistsException extends RuntimeException{
    public InputAlreadyExistsException(final String message) {
        super(message);
    }
}
