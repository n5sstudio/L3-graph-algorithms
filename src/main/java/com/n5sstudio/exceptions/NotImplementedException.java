package com.n5sstudio.exceptions;

public class NotImplementedException extends RuntimeException {

    private static final String DEFAULT_EXCEPTION_MESSAGE = "Not implemented";

    public NotImplementedException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}