package com.n5sstudio;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

class PathTest {

    private Graph graph;
    private Path path;

    @BeforeEach
    void setup() {
        this.graph = new Graph(5);
        try {
            this.graph.addVertex(1);
            this.graph.addVertex(2);
            this.graph.addArc(1, 2, 5);
            this.path = new Path(this.graph, 1, 2);
        } catch (VertexOutboundLimitException|VertexAlreadyExistsException| ArcAlreadyExistsException| VertexDoesNotExistsException e) {
            // Which is not expected in this setup
            throw new RuntimeException("Vertex outbound limit exceeded during setup", e);
        }
    }

    @Test
    void tesCreatePathWithWrongOriginNegative() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                path = new Path(graph, -1, 1);
            }
        });
    }

    @Test
    void tesCreatePathWithWrongOrigin() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                path = new Path(graph, 10, 1);
            }
        });
    }

    @Test
    void tesCreatePathWithWrongDestinationNegative() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                path = new Path(graph, 1, -1);
            }
        });
    }

    @Test
    void tesCreatePathWithWrongDestination() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                path = new Path(graph, 1, 10);
            }
        });
    }

    @Test
    void testBuildPathWithSameOriginAndDestination() throws VertexOutboundLimitException, VertexAlreadyExistsException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        this.path = new Path(this.graph, 1, 1);
        Graph computedPath = this.path.buildPath();
        assertEquals(1, computedPath.getVertexCount());
    }

    @Test
    void testBuildPathNoPath() throws VertexOutboundLimitException, VertexAlreadyExistsException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        this.path = new Path(this.graph, 2, 1);
        Graph computedPath = this.path.buildPath();
        assertEquals(0, computedPath.getVertexCount());
    }

    @Test
    void testBuildPath() throws VertexOutboundLimitException, VertexAlreadyExistsException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        this.graph.addVertex(3);
        this.graph.addArc(2, 3, 5);
        this.graph.addArc(3, 2, 5);
        this.path = new Path(this.graph, 1, 3);
        assertTrue(this.path.hasPath());
        Graph computedPath = this.path.buildPath();
        assertTrue(computedPath.hasArc(1, 2));
        assertTrue(computedPath.hasArc(2, 3));
        assertFalse(computedPath.hasArc(3, 2));
        assertEquals(3, computedPath.getVertexCount());
    }

}
