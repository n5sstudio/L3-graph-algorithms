package com.n5sstudio.exceptions;

public class ArcAlreadyExistsException extends Exception {
    
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Arc already exists";

    public ArcAlreadyExistsException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}
