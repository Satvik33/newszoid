package com.stackroute.registrationservice.exception;

public class UserAlreadyExistsException extends Exception {
    private String message;

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String string){
        super(string);
        this.message=string;
    }
}
