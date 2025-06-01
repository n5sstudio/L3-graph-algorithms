package com.n5sstudio.exceptions;

public class NotImplementedException extends Exception {

    private static final String DEFAULT_EXCEPTION_MESSAGE = "Not implemented";

    public NotImplementedException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}