package com.stackroute.searchservice.exceptions;

public class CategoryAlreadyExistsException extends Exception{
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }

    public CategoryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
