package com.banking.project.exception.validation;

public class NotEnoughFundsException extends InvalidInputException{
    public NotEnoughFundsException(final String message) {
        super(message);
    }
}
