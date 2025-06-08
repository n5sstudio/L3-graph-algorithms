package com.n5sstudio.exceptions;

public class FullStackException  extends RuntimeException {
    
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Stack is full";

    public FullStackException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}
