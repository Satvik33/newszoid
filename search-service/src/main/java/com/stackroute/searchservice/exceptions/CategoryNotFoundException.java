package com.stackroute.searchservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
