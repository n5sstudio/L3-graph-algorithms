package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Bellmann {
    
    private Graph graph;
    private int startingVertexId;
    private int[] minimumDistance;
    private int[] previousVertexId;

    private static final int INFINI = 1000000;

    public Bellmann(int depart0, Graph referenceGraph) throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        this.startingVertexId = depart0;
        this.graph = new Graph(referenceGraph);
        this.minimumDistance = new int[graph.getVertexCount()];
        this.previousVertexId = new int[graph.getVertexCount()];
    }

    private void initMinimumDistance() throws VertexOutboundLimitException {
        for (int i = 0; i < graph.getVertexCount(); i++) {
            minimumDistance[i] = getDistance(startingVertexId, i);
            previousVertexId[i] = startingVertexId;
        }
    }

    public int getMinimumDistance(int vertexId) {
        return minimumDistance[vertexId];
    }

    public int getDistance(int i, int j) throws VertexOutboundLimitException {
        if (graph.hasArc(i, j)) {
            return graph.getArcValue(i, j);
        } else {
            return INFINI;
        }
    }

    public void getShortestPath() throws VertexOutboundLimitException {
        initMinimumDistance();
        int cout;
        for (int k = 0; k < graph.getVertexCount(); k++) {
            for (int i = 0; i < graph.getVertexCount(); i++) {
                for (int j = 0; j < graph.getVertexCount(); j++) {
                    if (graph.hasArc(i, j)) {
                        cout = minimumDistance[i] + graph.getArcValue(i, j);
                        if (cout < minimumDistance[j]) {
                            minimumDistance[j] = cout;
                            previousVertexId[j] = i;
                        }
                    }
                }
            }
        }
    }
}
