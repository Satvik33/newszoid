package com.stackroute.contentservice.exception;

public class PostAlreadyExistsException extends Exception {
    public PostAlreadyExistsException(String message) {
        super(message);
    }

    public PostAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
