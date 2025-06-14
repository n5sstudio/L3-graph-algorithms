package com.n5sstudio.exceptions;

public class UnionGraphException extends Exception {
    
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Vertex already exists";

    public UnionGraphException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }

}
