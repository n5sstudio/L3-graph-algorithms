package com.n5sstudio.exceptions;

public class VertexOutboundLimitException extends Exception {
    
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Vertex out of the limit";

    public VertexOutboundLimitException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }
}
