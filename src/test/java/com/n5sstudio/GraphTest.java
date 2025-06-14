package com.n5sstudio;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.NotImplementedException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

class GraphTest {

    private Graph graph;

    @BeforeEach
    void setup() {
        graph = new Graph(5);
        try {
            graph.addVertex(1);
            graph.addVertex(2);
            graph.addArc(1, 2, 5);
        } catch (VertexAlreadyExistsException | ArcAlreadyExistsException | VertexOutboundLimitException | VertexDoesNotExistsException e) {
            // Which is not expected in this setup
            throw new RuntimeException("Vertex outbound limit exceeded during setup", e);
        }
    }

    @Test
    void testGraphDefault() {
        Graph h = new Graph();
        assertEquals(1000, h.getMaximumNumberOfVertex());
        assertEquals(0, h.getUndefiledValue());
    }

    @Test
    void testGraphMatrix() throws Exception {
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = graph.getUndefiledValue();
            }
        }
        matrix[1][2] = 3;

        Graph h = new Graph(matrix);
        assertEquals(6, h.getMaximumNumberOfVertex());
        assertEquals(0, h.getUndefiledValue());
        assertTrue(h.hasVertex(1));
        assertTrue(h.hasVertex(2));
        assertFalse(h.hasVertex(0));
        assertFalse(h.hasVertex(3));
        assertTrue(h.hasArc(1, 2));
        assertEquals(3, h.getArcValue(1, 2));
        assertFalse(h.hasArc(0, 2));
    }

    @Test
    void testGraphCopy() throws Exception {
        Graph h = new Graph(graph);
        assertTrue(h.hasVertex(1));
        assertTrue(h.hasVertex(2));
        assertTrue(h.hasArc(1, 2));
        assertEquals(5, h.getArcValue(1, 2));
        assertFalse(h.hasVertex(0));
        assertFalse(h.hasVertex(3));
        assertFalse(h.hasArc(2, 1));
    }

    @Test
    void testHasVertex() throws Exception {
        assertTrue(graph.hasVertex(1));
        assertFalse(graph.hasVertex(3));
    }

    @Test
    void testGetNumberOfVertex() throws Exception {
        assertEquals(2, graph.getVertexCount());
            graph.addVertex(3);
        assertEquals(3, graph.getVertexCount());
        graph.deleteVertex(3);
            graph.deleteVertex(2);
            graph.deleteVertex(1);
        assertEquals(0, graph.getVertexCount());
    }

    @Test
    void testHasVertexOutboundLimit() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.hasVertex(100);
            }
        });
    }

    @Test
    void testHasVertexOutboundNegativeValueLimit() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.hasVertex(-1);
            }
        });
    }
    @Test
    void testAddVertexThatAlreadyExists() {
        assertThrows(VertexAlreadyExistsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.addVertex(3);
                assertTrue(graph.hasVertex(3));
                graph.addVertex(3); 
            }
        });
    }

    @Test
    void testAddVertexOutboundLimits() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.addVertex(100);
            }
        });
    }

    @Test
    void testAddVertexOutboundNegativeValueLimit() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.addVertex(-1);
            }
        });
    }

    @Test
    void testAddArc() throws Exception {
        graph.addVertex(3);
        graph.addArc(1, 3, 5);
        assertTrue(graph.hasArc(1, 3));
    }

    @Test
    void testDeleteVertexOutboundLimits() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.deleteVertex(100);
            }
        });
    }

    @Test
    void testDeleteVertex() throws Exception {
        graph.deleteVertex(2);
        assertFalse(graph.hasVertex(2));
        assertEquals(1, graph.getVertexCount());
    }

    @Test
    void testDeleteVertexThatDoesNotExists() throws Exception {
        assertFalse(graph.hasVertex(3));
        graph.deleteVertex(3);
        assertFalse(graph.hasVertex(3));
    }

    @Test
    void testHasArc() throws Exception {
        assertTrue(graph.hasArc(1, 2));
        graph.addVertex(3);
        assertFalse(graph.hasArc(1, 3));
    }


    @Test
    void testDeleteVertexOutboundNegativeValueLimits() {
        assertThrows(VertexOutboundLimitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.deleteVertex(-1);
            }
        });
    }

    @Test
    void testAddArcThatAlreadyExists() {
        assertThrows(ArcAlreadyExistsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.addArc(1, 2, 5);
            }
        });
    }

    @Test
    void testAddArcWithDestVertexDoesNotExists() {
        assertThrows(VertexDoesNotExistsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.addArc(1, 3, 5);
            }
        });
    }

    @Test
    void testAddArcWithOriginVertexDoesNotExists() {
        assertThrows(VertexDoesNotExistsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.addArc(3, 1, 5);
            }
        });
    }

    @Test
    void testGetArcValue() throws Exception {
        assertEquals(5, graph.getArcValue(1, 2));
        graph.addVertex(3);
        assertEquals(graph.getUndefiledValue(), graph.getArcValue(1, 3));
        graph.deleteVertex(2);
        assertEquals(graph.getUndefiledValue(), graph.getArcValue(1, 2));
    }

    @Test
    void testDeleteArc() throws Exception {
        graph.deleteArc(1, 2);
        assertEquals(graph.getUndefiledValue(), graph.getArcValue(1, 2));
        assertTrue(graph.hasVertex(1));
        assertTrue(graph.hasVertex(2));
    }

    @Test
    void testDeleteArcThatDoesNotExist() throws Exception {
        assertFalse(graph.hasArc(2, 1));
        assertFalse(graph.deleteArc(2, 1));
    }

    @Test
    void testGetVertexOutDegree() {
        assertEquals(1, graph.getVertexOutDegree(1));
        assertEquals(0, graph.getVertexOutDegree(2));
    }

    @Test
    void testGetVertexInDegree() {
        assertEquals(0, graph.getVertexInDegree(1));
        assertEquals(1, graph.getVertexInDegree(2));
    }

    @Test
    void testGetVertexDegree() {
        assertEquals(1, graph.getVertexDegree(1));
        assertEquals(1, graph.getVertexDegree(2));
    }

    @Test
    void testGetSuccessorList() throws Exception {
        int size = graph.getMaximumNumberOfVertex();
        boolean[] list = graph.getSuccessorBooleanList(1);

        boolean[] attemptList = new boolean[size];
        for (int k = 0; k < size; k++) {
            attemptList[k] = false;
        }
        attemptList[2] = true;
        assertArrayEquals(attemptList, list);

    }

    @Test
    void testGetPredecessorList() throws Exception {
        int size = graph.getMaximumNumberOfVertex();
        boolean[] list = graph.getPredecessorBooleanList(2);

        boolean[] attemptList = new boolean[size];
        for (int k = 0; k < size; k++) {
            attemptList[k] = false;
        }
        attemptList[1] = true;
        assertArrayEquals(attemptList, list);

    }

    @Test
    void testIReflexive() throws Exception {
        assertFalse(graph.isReflexive());
        graph.addArc(1, 1, 1);
        assertFalse(graph.isReflexive());
        graph.addArc(2, 2, 2);
        assertTrue(graph.isReflexive());
    }

    @Test
    void testIsIrreflexive() throws Exception {
        assertTrue(graph.isIrreflexive());
        graph.addArc(1, 1, 1);
        assertFalse(graph.isIrreflexive());
    }

    @Test
    void testIsSymmetric() throws Exception {
        assertFalse(graph.isSymmetric());
        graph.addArc(2, 1, 5);
        assertTrue(graph.isSymmetric());
    }

    @Test
    void testIsAntisymmetric() throws Exception {
        assertTrue(graph.isAntisymmetric());
        graph.addArc(2, 1, 5);
        assertFalse(graph.isAntisymmetric());
    }

    @Test
    void testTransitive() throws Exception {
        graph.addVertex(3);
        graph.addArc(2, 3, 1);
        assertFalse(graph.isTransitive());
        graph.addArc(1, 3, 1);
        assertTrue(graph.isTransitive());
    }

    @Test
    void testAntiTransitive() throws Exception {
        graph.addVertex(3);
        graph.addArc(2, 3, 1);
        assertTrue(graph.isAntiTransitive());
        graph.addArc(1, 3, 1);
        assertFalse(graph.isAntiTransitive());
    }

    @Test
    void testTranspose() throws Exception {
        assertTrue(graph.hasArc(1, 2));
        graph.transpose();
        assertFalse(graph.hasArc(1, 2));
        assertTrue(graph.hasArc(2, 1));
    }

    @Test
    void testUnion() {
        assertThrows(NotImplementedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.union(graph, graph);
            }
        });
    }

    @Test
    void testComposition() {
        assertThrows(NotImplementedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.composition(graph, graph);
            }
        });
    }

    @Test
    void testSubGraph() {
        assertThrows(NotImplementedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                graph.subgraph(graph, graph);
            }
        });
    }

}
