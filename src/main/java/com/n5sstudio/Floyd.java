package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.ArcDoesNotExistException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Floyd {

    private Graph graph;

    public Floyd(Graph g0)
            throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        graph = new Graph(g0);
    }

    private Graph shortestPath() throws VertexOutboundLimitException, ArcAlreadyExistsException,
            VertexDoesNotExistsException, ArcDoesNotExistException {
        Graph computedGraph = new Graph(graph);
        for (int x = 0; x < computedGraph.getVertexCount(); x++) {
            for (int y = 0; y < computedGraph.getVertexCount(); y++) {
                for (int z = 0; z < computedGraph.getVertexCount(); z++) {
                    addComputedArc(computedGraph, x, y, z);
                }
            }
        }
        return computedGraph;
    }

    private void addComputedArc(Graph computedGraph, int x, int y, int z) throws VertexOutboundLimitException, ArcDoesNotExistException {
        int xy;
        int yz;
        int xz;
        if (computedGraph.hasArc(x, y)) {
            xy = computedGraph.getArcValue(x, y);
            if (computedGraph.hasArc(y, z)) {
                yz = computedGraph.getArcValue(y, z);
                if (computedGraph.hasArc(x, z)) {
                    xz = computedGraph.getArcValue(x, z);
                    computedGraph.updateArcValue(x, z, Math.min(xy + yz, xz));
                } else {
                    computedGraph.updateArcValue(y, z, xy + yz);
                }
            }
        }
    }

    public Integer getMinimumDistance(int i, int j) throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException, ArcDoesNotExistException {
        Graph computedGraph = this.shortestPath();
        if (computedGraph.hasArc(i, j)) {
            return computedGraph.getArcValue(i, j);
        }
        return Dijkstra.INFINI;
    }
}
