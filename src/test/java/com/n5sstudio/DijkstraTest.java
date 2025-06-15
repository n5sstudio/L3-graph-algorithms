package com.n5sstudio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

class DijkstraTest {

    private Graph graph;
    private Dijkstra dijkstra;

    @BeforeEach
    void setup() {
        try {
            graph = new Graph(5);
            graph.addVertex(0);
            graph.addVertex(1);
            graph.addVertex(2);
            graph.addVertex(3);
            graph.addVertex(4);
            graph.addArc(0, 1, 1);
            graph.addArc(1, 2, 1);
            graph.addArc(2, 3, 1);
            graph.addArc(3, 4, 1);
            graph.addArc(0, 4, 3);
        } catch (VertexAlreadyExistsException | ArcAlreadyExistsException | VertexOutboundLimitException
                | VertexDoesNotExistsException e) {
            // Which is not expected in this setup
            throw new RuntimeException("No Exception during the setup", e);
        }
    }

    @Test
    void testGetShortestPath() {
        try {
            dijkstra = new Dijkstra(0, graph);
            assertEquals(3, dijkstra.getMinimumDistance(4));
        } catch (Throwable e) {
            throw new RuntimeException("No Exception during this test", e);
        }
    }

}
