package com.n5sstudio.exceptions;

public class EmptyStackException  extends RuntimeException {
    
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Stack is empty";

    public EmptyStackException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}
