package com.banking.project.exception.validation;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(final String message){
        super(message);
    }
}
