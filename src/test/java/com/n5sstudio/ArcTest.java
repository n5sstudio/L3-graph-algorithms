package com.n5sstudio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArcTest {
    
    private Arc arc;


    @BeforeEach
    void setup() {
        arc = new Arc(1, 2, 3);
    }

    @Test
    void testCopy() {
        Arc copiedArc = new Arc(arc);
        assertEquals(arc.getOrigin(), copiedArc.getOrigin());
        assertEquals(arc.getDestination(), copiedArc.getDestination());
        assertEquals(arc.getValue(), copiedArc.getValue());
    }

}