package com.n5sstudio;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.n5sstudio.exceptions.EmptyStackException;
import com.n5sstudio.exceptions.FullStackException;

class StackTest {

    private Stack stack;

    @BeforeEach
    void setup() {
        this.stack = new Stack(5);
    }

    @Test
    void testStackIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testGetSize() {
        assertEquals(0, stack.getSize());
        stack.push(10);
        assertEquals(1, stack.getSize());
    }

    @Test
    void testIsFull() {
        assertFalse(stack.isFull());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertTrue(stack.isFull());
    }

    @Test
    void testPushInFullStack() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertThrows(FullStackException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                stack.push(6); 
            }
        });
    }

    @Test
    void testPop() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPopInEmptyStack() {
        assertThrows(EmptyStackException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                stack.pop(); 
            }
        });
    }

    @Test
    void testPeek() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.peek());
        assertEquals(2, stack.peek());
    }

    @Test
    void testContains() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertTrue(stack.contains(stack, 1));
        assertFalse(stack.contains(stack, 0));
    }

}
