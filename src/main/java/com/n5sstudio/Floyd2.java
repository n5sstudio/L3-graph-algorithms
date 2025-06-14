package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.ArcDoesNotExistException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Floyd {
    
    private Graph graph;
    private int[][] previousVertexId;
    private static int ALPHA_NOTDEF = 1000;

    public Floyd(Graph g0) throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
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

    public void PlusCourtCheminWk() throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException, ArcDoesNotExistException {
        Graph g2 = new Graph(graph);
        int v1;
        int v2;
        int w;
        initRoutage();
        for (int x = 0; x < g2.getVertexCount(); x++) {
            for (int y = 0; y < g2.getVertexCount(); y++) {
                v1 = g2.getArcValue(y, x);
                if (v1 != ALPHA_NOTDEF) {
                    for (int z = 0; z < g2.getVertexCount(); z++) {
                        v2 = g2.getArcValue(x, z);
                        if (v2 != ALPHA_NOTDEF) {
                            w = g2.getArcValue(y, z);
                            if (w != ALPHA_NOTDEF) {
                                if ((v1 + v2) < w) {
                                    g2.updateArcValue(y, z, v1 + v2);
                                    previousVertexId[y][z] = previousVertexId[x][z];
                                } else {
                                    g2.addArc(y, z, v1 + v2);
                                    previousVertexId[y][z] = previousVertexId[x][z];
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
