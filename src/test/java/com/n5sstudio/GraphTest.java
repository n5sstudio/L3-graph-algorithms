package com.n5sstudio;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.n5sstudio.exceptions.NotImplementedException;

class GraphTest {

    private Graph g;

    @BeforeEach
    void setup() {
        g = new Graph(5);
        g.addVertex(1);
        g.addVertex(2);
        g.addArc(1, 2, 5);
    }

    @Test
    void testGraphDefault() {
        Graph h = new Graph();
        assertEquals(1000, h.getMaximumNumberOfVertex());
        assertEquals(0, h.getUndefiledValue());
    }

    @Test
    void testGraphMatrix() {
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = g.getUndefiledValue();
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
    void testGraphCopy() {
        Graph h = new Graph(g);
        assertTrue(h.hasVertex(1));
        assertTrue(h.hasVertex(2));
        assertTrue(h.hasArc(1, 2));
        assertEquals(5, h.getArcValue(1, 2));
        assertFalse(h.hasVertex(0));
        assertFalse(h.hasVertex(3));
        assertFalse(h.hasArc(2, 1));
    }

    @Test
    void testHasVertex() {
        assertTrue(g.hasVertex(1));
        assertFalse(g.hasVertex(3));
    }

    @Test
    void testVertexCount() {
        assertEquals(2, g.getVertexCount());
    }

    @Test
    void testAddVertex() {
        g.addVertex(3);
        assertTrue(g.hasVertex(3));
        assertEquals(3, g.getVertexCount());
    }

    @Test
    void testDeleteVertex() {
        g.deleteVertex(2);
        assertFalse(g.hasVertex(2));
        assertEquals(1, g.getVertexCount());
    }

    @Test
    void testHasArc() {
        assertTrue(g.hasArc(1, 2));
        g.addVertex(3);
        assertFalse(g.hasArc(1, 3));
    }

    @Test
    void testGetArcValue() {
        assertEquals(5, g.getArcValue(1, 2));
        g.addVertex(3);
        assertEquals(g.getUndefiledValue(), g.getArcValue(1, 3));
        g.deleteVertex(2);
        assertEquals(g.getUndefiledValue(), g.getArcValue(1, 2));
    }

    @Test
    void testDeleteArc() {
        g.deleteArc(1, 2);
        assertEquals(g.getUndefiledValue(), g.getArcValue(1, 2));
        assertTrue(g.hasVertex(1));
        assertTrue(g.hasVertex(2));
    }

    @Test
    void testGetVertexOutDegree() {
        assertEquals(1, g.getVertexOutDegree(1));
        assertEquals(0, g.getVertexOutDegree(2));
    }

    @Test
    void testGetVertexInDegree() {
        assertEquals(0, g.getVertexInDegree(1));
        assertEquals(1, g.getVertexInDegree(2));
    }

    @Test
    void testGetVertexDegree() {
        assertEquals(1, g.getVertexDegree(1));
        assertEquals(1, g.getVertexDegree(2));
    }

    @Test
    void testGetSuccessorList() {
        int size = g.getMaximumNumberOfVertex();
        int[] list = g.getSuccessorList(1);

        int[] attemptList = new int[size];
        for (int k = 0; k < size; k++) {
            attemptList[k] = g.getUndefiledValue();
        }
        attemptList[2] = 2;
        assertArrayEquals(attemptList, list);

    }

    @Test
    void testGetPredecessorList() {
        int size = g.getMaximumNumberOfVertex();
        int[] list = g.getPredecessorList(2);

        int[] attemptList = new int[size];
        for (int k = 0; k < size; k++) {
            attemptList[k] = g.getUndefiledValue();
        }
        attemptList[1] = 1;
        assertArrayEquals(attemptList, list);

    }

    @Test
    void testIReflexive() {
        assertFalse(g.isReflexive());
        g.addArc(1, 1, 1);
        assertFalse(g.isReflexive());
        g.addArc(2, 2, 2);
        assertTrue(g.isReflexive());
    }

    @Test
    void testIsIrreflexive() {
        assertTrue(g.isIrreflexive());
        g.addArc(1, 1, 1);
        assertFalse(g.isIrreflexive());
    }

    @Test
    void testIsSymmetric() {
        assertFalse(g.isSymmetric());
        g.addArc(2, 1, 5); 
        assertTrue(g.isSymmetric());
    }

    @Test
    void testIsAntisymmetric() {
        assertTrue(g.isAntisymmetric());
        g.addArc(2, 1, 5); 
        assertFalse(g.isAntisymmetric());
    }

    @Test
    void testTransitive() {
        g.addVertex(3);
        g.addArc(2, 3, 1);
        assertFalse(g.isTransitive());
        g.addArc(1, 3, 1); 
        assertTrue(g.isTransitive());
    }

    @Test
    void testAntiTransitive() {
        g.addVertex(3);
        g.addArc(2, 3, 1);
        assertTrue(g.isAntiTransitive());
        g.addArc(1, 3, 1);
        assertFalse(g.isAntiTransitive());
    }

    @Test
    void testTranspose() {
        assertTrue(g.hasArc(1, 2));
        g.transpose();
        assertFalse(g.hasArc(1, 2));
        assertTrue(g.hasArc(2, 1));
    }

    @Test
    void testUnion() {
        assertThrows(NotImplementedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                g.union(g, g);
            }
        });
    }

    @Test
    void testComposition() {
        assertThrows(NotImplementedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                g.composition(g, g);
            }
        });
    }

    @Test
    void testSubGraph() {
        assertThrows(NotImplementedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                g.subgraph(g, g);
            }
        });
    }

}
