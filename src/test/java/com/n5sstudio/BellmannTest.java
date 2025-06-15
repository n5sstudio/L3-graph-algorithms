package com.n5sstudio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class BellmannTest {

    private Graph graph;
    private Bellmann bellmann;

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
            bellmann = new Bellmann(0, graph);
        } catch (VertexAlreadyExistsException | ArcAlreadyExistsException | VertexOutboundLimitException
                | VertexDoesNotExistsException e) {
            // Which is not expected in this setup
            throw new RuntimeException("No Exception during the setup", e);
        }
    }

    @Test
    void testGetShortestPath() {
        try {
            bellmann.getShortestPath();
            assertEquals(3, bellmann.getMinimumDistance(4));
        } catch (VertexOutboundLimitException e) {
            throw new RuntimeException("No Exception during this test", e);
        }
    }

}
