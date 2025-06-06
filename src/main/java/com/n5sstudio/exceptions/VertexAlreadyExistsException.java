package com.n5sstudio.exceptions;

public class VertexAlreadyExistsException extends Exception {
    
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Vertex already exists";

    public VertexAlreadyExistsException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}
