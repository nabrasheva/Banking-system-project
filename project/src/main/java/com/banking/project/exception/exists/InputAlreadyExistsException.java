package com.banking.project.exception.exists;

public class InputAlreadyExistsException extends RuntimeException{
    InputAlreadyExistsException(final String message){
        super(message);
    }
}
