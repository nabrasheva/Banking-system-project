package com.banking.project.exception.notfound;

public class SafeNotFoundException extends InputNotFoundException {
    public SafeNotFoundException(final String message) {
        super(message);
    }
}
