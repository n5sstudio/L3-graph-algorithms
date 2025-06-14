package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Path {
    
    private Graph referenceGraph;

    private int originVertexIdPath;
    private int destinationVertexIdPath;
    private int[] previousVertex;

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
        this.previousVertex = new int[referenceGraph.getMaximumNumberOfVertex()];
    }

    public boolean hasPath() throws VertexOutboundLimitException {
        Stack stack = new Stack(referenceGraph.getMaximumNumberOfVertex());
        stack.push(this.originVertexIdPath);
        boolean[] visited = new boolean[referenceGraph.getMaximumNumberOfVertex()];
        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            if (currentVertex == this.destinationVertexIdPath) {
                return true;
            }
            boolean[] successorList = referenceGraph.getSuccessorBooleanList(currentVertex);
            for (int neighbor = 0; neighbor < referenceGraph.getMaximumNumberOfVertex(); neighbor++) {
                if (successorList[neighbor]) {
                    visited[neighbor] = true;
                    this.previousVertex[neighbor] = currentVertex;
                    stack.push(neighbor);
                }
            }
        }
        return false;
    }
    
    public Graph buildPath() throws VertexOutboundLimitException, VertexAlreadyExistsException, ArcAlreadyExistsException, VertexDoesNotExistsException  {
        Graph computedPath = new Graph(referenceGraph.getMaximumNumberOfVertex());
        if (this.hasPath()) {
            int currentVertex = this.destinationVertexIdPath;
            computedPath.addVertex(currentVertex);
            while (currentVertex != this.originVertexIdPath) {
                computedPath.addVertex(this.previousVertex[currentVertex]);
                computedPath.addArc(this.previousVertex[currentVertex], currentVertex, referenceGraph.getArcValue(this.previousVertex[currentVertex], currentVertex));
                currentVertex = this.previousVertex[currentVertex];
            }
        }
        return computedPath;
    }
}
