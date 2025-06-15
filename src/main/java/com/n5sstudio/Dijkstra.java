package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Dijkstra {

    private Graph graph;
    private Graph treeGraph;
    private int startingVertexId;
    private boolean[] visitedVertex;
    private int[] distanceFromVertexRoot;

    public static final int INFINI = 1000000;

    public Dijkstra(int startingVertexId0, Graph g0)
            throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        this.startingVertexId = startingVertexId0;
        this.graph = new Graph(g0);
        this.treeGraph = new Graph(g0.getMaximumNumberOfVertex());
        this.visitedVertex = new boolean[graph.getMaximumNumberOfVertex()];
        this.distanceFromVertexRoot = new int[graph.getMaximumNumberOfVertex()];
        initVisited();
        initDistaceFromVertexRoot();
    }

    private int getDistance(int j) {
        return distanceFromVertexRoot[j];
    }

    private void visitVertex(int j) {
        if (!visitedVertex[j]) {
            visitedVertex[j] = true;
        }
    }

    private boolean isVisited(int j) {
        return visitedVertex[j];
    }

    private void initDistaceFromVertexRoot() {
        for (int i = 0; i < graph.getVertexCount(); i++) {
            distanceFromVertexRoot[i] = INFINI;
        }
        distanceFromVertexRoot[startingVertexId] = 0;
    }

    private void initVisited() {
        for (int i = 0; i < graph.getVertexCount(); i++) {
            visitedVertex[i] = false;
        } 
        visitedVertex[startingVertexId] = true;
    }

    private int vertexChoice() throws VertexOutboundLimitException {
        int k = 0;
        for (int i = 0; i < graph.getVertexCount(); i++) {
            for (int j = 0; j < graph.getVertexCount(); j++) {
                if ((isVisited(i) && !isVisited(j)) && graph.hasArc(i, j)) {
                    int distance = getDistance(i) + graph.getArcValue(i, j);
                    if (distance < distanceFromVertexRoot[j]) {
                        distanceFromVertexRoot[j] = distance;
                        k = j;
                    }
                }
            }
        }
        return k;
    }

    private void getShortestPath() throws VertexOutboundLimitException, VertexAlreadyExistsException,
            ArcAlreadyExistsException, VertexDoesNotExistsException {
        int currentVertex = startingVertexId;
        this.visitVertex(currentVertex);
        this.treeGraph.addVertex(currentVertex);
        for (int i = 0; i < graph.getVertexCount(); i++) {
            int nextVertex = vertexChoice();
            if (!isVisited(nextVertex)) {
                this.visitVertex(nextVertex);
                this.treeGraph.addVertex(nextVertex);
            }
            this.treeGraph.addArc(currentVertex, nextVertex, getDistance(currentVertex) + graph.getArcValue(currentVertex, nextVertex));
            currentVertex = nextVertex;
        }
    }

    public int getMinimumDistance(int i) throws VertexOutboundLimitException, VertexAlreadyExistsException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        getShortestPath();
        if (treeGraph.hasVertex(i)) {
            Path path = new Path(treeGraph, startingVertexId, i);
            Graph computedPath = path.buildPath();
            return computedPath.getWeight();
        }
        return INFINI;
    }
}
