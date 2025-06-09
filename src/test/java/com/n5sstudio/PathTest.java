package com.n5sstudio;

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
    void tesCreatePathWithWrongOrigin() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                path = new Path(graph, -1, 1);
            }
        });
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                path = new Path(graph, 10, 1);
            }
        });
    }

    @Test
    void tesCreatePathWithWrongDestination() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                path = new Path(graph, 1, -1);
            }
        });
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                path = new Path(graph, 1, 10);
            }
        });
    }

    @Test
    void testBuildPathWithSameOriginAndDestination() throws VertexOutboundLimitException, VertexAlreadyExistsException {
        this.path = new Path(this.graph, 1, 1);
        Graph computedPath = this.path.buildPath();
        assertEquals(1, computedPath.getNumberOfVertex());
    }

    @Test
    void testBuildPath() throws VertexOutboundLimitException, VertexAlreadyExistsException {
        // TODO: Implement the logic to build the path
        this.path = new Path(this.graph, 1, 2);
        Graph computedPath = this.path.buildPath();
        assertEquals(0, computedPath.getNumberOfVertex());
    }

}
