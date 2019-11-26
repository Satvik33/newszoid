package com.stackroute.searchservice.exceptions;

public class LocationAlreadyExistsException extends Exception {
    public LocationAlreadyExistsException(String message) {
        super(message);
    }

    public LocationAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
