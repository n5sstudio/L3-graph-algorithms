package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.ArcDoesNotExistException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Floyd {

    private Graph graph;
    private int[][] previousVertexId;

    private static final int ALPHA_NOTDEF = 1000;

    public Floyd(Graph g0)
            throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        graph = new Graph(g0);
    }

    public void initRoutage() throws VertexOutboundLimitException {
        for (int x = 0; x < graph.getVertexCount(); x++) {
            for (int y = 0; y < graph.getVertexCount(); y++) {
                if (graph.hasArc(x, y)) {
                    previousVertexId[x][y] = x;
                } else {
                    previousVertexId[x][y] = ALPHA_NOTDEF;
                }
            }
        }
    }

    public Graph shortestPath() throws VertexOutboundLimitException, ArcAlreadyExistsException,
            VertexDoesNotExistsException, ArcDoesNotExistException {
        Graph comptedGraph = new Graph(graph);
        int v1;
        int v2;
        initRoutage();
        for (int x = 0; x < comptedGraph.getVertexCount(); x++) {
            for (int y = 0; y < comptedGraph.getVertexCount(); y++) {
                if (comptedGraph.hasArc(y, x)) {
                    v1 = comptedGraph.getArcValue(y, x);
                    for (int z = 0; z < comptedGraph.getVertexCount(); z++) {
                        if (comptedGraph.hasArc(x, z)) {
                            v2 = comptedGraph.getArcValue(x, z);
                            if (comptedGraph.hasArc(y, z) && (v1 + v2) < comptedGraph.getArcValue(y, z)) {
                                comptedGraph.updateArcValue(y, z, v1 + v2);
                                previousVertexId[y][z] = previousVertexId[x][z];
                            }
                            if (comptedGraph.hasArc(y, z) && (v1 + v2) >= comptedGraph.getArcValue(y, z)) {
                                comptedGraph.addArc(y, z, v1 + v2);
                                previousVertexId[y][z] = previousVertexId[x][z];
                            }
                        }
                    }
                }
            }
        }
        return comptedGraph;
    }
}
