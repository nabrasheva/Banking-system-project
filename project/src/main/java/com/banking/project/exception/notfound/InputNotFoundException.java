package com.banking.project.exception.notfound;

public class InputNotFoundException extends RuntimeException{
    public InputNotFoundException(final String message){
        super(message);
    }
}
