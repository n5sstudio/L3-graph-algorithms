package com.n5sstudio.exceptions;

public class ArcDoesNotExistException extends Exception {
    
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Arc does not exist";

    public ArcDoesNotExistException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}
