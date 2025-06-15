package com.n5sstudio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

class FloydTest {

    private Graph graph;
    private Floyd floyd;

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
            floyd = new Floyd(graph);
            assertEquals(3, floyd.getMinimumDistance(0, 4));
        } catch (Throwable e) {
            throw new RuntimeException("No Exception during this test", e);
        }
    }

    @Test
    void testGetShortestPathThatDoesNotExist() {
        try {
            floyd = new Floyd(graph);
            assertEquals(Dijkstra.INFINI, floyd.getMinimumDistance(4, 0));
        } catch (Throwable e) {
            throw new RuntimeException("No Exception during this test", e);
        }
    }

}
