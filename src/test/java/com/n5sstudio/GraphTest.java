package com.n5sstudio;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {

    private Graph g;

    @BeforeEach
    public void setup() {
        g = new Graph(5);
        g.addVertex(1);
        g.addVertex(2);
        g.addArc(1, 2, 5);
    }

    @Test
    public void testHasVertex() {
        assertTrue(g.hasVertex(1));
        assertFalse(g.hasVertex(3));
    }

    @Test
    public void testVertexCount() {
        assertEquals(2, g.getVertexCount());
    }

    @Test
    public void testAddVertex() {
        g.addVertex(3);
        assertTrue(g.hasVertex(3));
        assertEquals(3, g.getVertexCount());
    }

    @Test
    public void testDeleteVertex() {
        g.deleteVertex(2);
        assertFalse(g.hasVertex(2));
        assertEquals(1, g.getVertexCount());
    }

    @Test
    public void testHasArc() {
        assertTrue(g.hasArc(1, 2));
        g.addVertex(3);
        assertFalse(g.hasArc(1, 3));
    }

    @Test
    public void testGetArcValue() {
        assertEquals(5, g.getArcValue(1, 2));
        g.addVertex(3);
        assertEquals(g.getUndefiledValue(), g.getArcValue(1, 3));
        g.deleteVertex(2);
        assertEquals(g.getUndefiledValue(), g.getArcValue(1, 2));
    }

    @Test
    public void testGetVertexOutDegree() {
        assertEquals(1, g.getVertexOutDegree(1));
        assertEquals(0, g.getVertexOutDegree(2));
    }

    @Test
    public void testGetVertexInDegree() {
        assertEquals(0, g.getVertexInDegree(1));
        assertEquals(1, g.getVertexInDegree(2));
    }

    @Test
    public void testGetVertexDegree() {
        assertEquals(1, g.getVertexDegree(1));
        assertEquals(1, g.getVertexDegree(2));
    }

    @Test
    public void testIsReflexive() {
        
    }

    @Test
    public void testIsIrreflexive() {
        
    }

    @Test
    public void testIsSymetric() {
        
    }

    @Test
    public void testIsAntiSymetric() {
        
    }

    @Test
    public void testIsTransitive() {
        
    }

    @Test
    public void testIsAntiTransitive() {
        
    }

}
