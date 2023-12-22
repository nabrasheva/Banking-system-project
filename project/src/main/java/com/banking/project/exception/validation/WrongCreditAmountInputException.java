package com.banking.project.exception.validation;

public class WrongCreditAmountInputException extends InvalidInputException{
    public WrongCreditAmountInputException(final String message) {
        super(message);
    }
}
