package com.n5sstudio;

import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Path {
    
    private Graph referenceGraph;

    private int originVertexIdPath;
    private int destinationVertexIdPath;

    public Path(Graph referenceGraph, int originVertexIdPath, int destinationVertexIdPath)
            throws VertexOutboundLimitException {

        this.referenceGraph = referenceGraph;
        if (originVertexIdPath < 0 || originVertexIdPath >= referenceGraph.getMaximumNumberOfVertex()) {
            throw new VertexOutboundLimitException();
        }
        this.originVertexIdPath = originVertexIdPath;
        if (destinationVertexIdPath < 0 || destinationVertexIdPath >= referenceGraph.getMaximumNumberOfVertex()) {
            throw new VertexOutboundLimitException();
        }
        this.destinationVertexIdPath = destinationVertexIdPath;
    }
    
    public Graph buildPath() throws VertexOutboundLimitException, VertexAlreadyExistsException  {
        Graph computedPath = new Graph(referenceGraph.getMaximumNumberOfVertex());
        if (this.originVertexIdPath == this.destinationVertexIdPath) {
            computedPath.addVertex(originVertexIdPath);
        }
        return computedPath;
    }
}
