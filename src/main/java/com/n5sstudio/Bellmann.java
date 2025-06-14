package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Bellmann {
    
    private Graph graph;
    private int startingVertexId;
    private int[] minimumDistance;
    private int[] previousVertexId;
    private int INFINI = 1000000;
    private boolean stable;

    public Bellmann(int depart0, Graph referenceGraph) throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        this.startingVertexId = depart0;
        this.graph = new Graph(referenceGraph);
        this.minimumDistance = new int[graph.getVertexCount()];
        this.previousVertexId = new int[graph.getVertexCount()];
    }

    public int[] initMinimumDistance() throws VertexOutboundLimitException {
        for (int i = 0; i < graph.getVertexCount(); i++) {
            minimumDistance[i] = getDistance(startingVertexId, i);
            previousVertexId[i] = startingVertexId;
        }
        return minimumDistance;
    }

    public int getDistance(int i, int j) throws VertexOutboundLimitException, VertexOutboundLimitException {
        if (graph.hasArc(i, j) == true) {
            return graph.getArcValue(i, j);
        } else {
            return INFINI;
        }
    }

    public void getShortestPath() throws VertexOutboundLimitException, VertexOutboundLimitException {
        initMinimumDistance();
        int k = 1;
        stable = false;
        int cout;
        while (!stable && k <= graph.getVertexCount()) {
            int[] D_anc = new int[graph.getVertexCount()];
            k = k + 1;
            stable = true;
            D_anc = minimumDistance;
            for (int i = 0; i < graph.getVertexCount(); i++) {
                for (int j = 0; j < graph.getVertexCount(); j++) {
                    cout = D_anc[j] + graph.getArcValue(j, i);
                    if (cout < minimumDistance[i]) {
                        minimumDistance[i] = cout;
                        previousVertexId[i] = j;
                        stable = false;
                    }
                }
            }
        }
    }
}
