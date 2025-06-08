package com.n5sstudio.exceptions;

public class VertexDoesNotExistsException extends Exception {
    
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Vertex already exists";

    public VertexDoesNotExistsException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}
