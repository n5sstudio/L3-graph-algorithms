package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Dijkstra {
    
    private Graph graph;
    private int startingVertexId;
    private int[] minimumDistance;
    private boolean[] visitedVertex;

    private static final int INFINI = 1000000;

    public Dijkstra(int startingVertexId0, Graph g0) throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        startingVertexId = startingVertexId0;
        graph = new Graph(g0);
        visitedVertex = new boolean[graph.getVertexCount()];
        initMinimumDistance();
    }

    public int getDistance(int i, int j) throws VertexOutboundLimitException {
        if (graph.hasArc(i, j)) {
            return graph.getArcValue(i, j);
        } else {
            return INFINI;
        }
    }

    public int[] initMinimumDistance() throws VertexOutboundLimitException {
        minimumDistance = new int[graph.getVertexCount()];
        for (int i = 0; i < graph.getVertexCount(); i++) {
            minimumDistance[i] = getDistance(startingVertexId, i);
        }
        return minimumDistance;
    }

    public void visitVertex(int j) {
        if (!visitedVertex[j]) {
            visitedVertex[j] = true;
        }
    }

    public boolean isVisited(int j) {
        return visitedVertex[j];
    }

    public int vertexChoice() {
        int min = INFINI;
        int k = 0;
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (minimumDistance[i] < min && !isVisited(i)) {
                min = minimumDistance[i];
                k = i;
            }
        }
        return k;
    }
}
