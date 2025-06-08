package com.n5sstudio;

import com.n5sstudio.exceptions.EmptyStackException;
import com.n5sstudio.exceptions.FullStackException;

public class Stack {

    private int[] stackContent;
    private int stackSize;
    private int stackMaximumSize;

    public Stack() {
        this.stackMaximumSize = 1000;
        this.stackSize = -1;
        this.stackContent = new int[stackMaximumSize];
    }

    public Stack(int taille) {
        this.stackMaximumSize = taille;
        this.stackSize = -1;
        this.stackContent = new int[stackMaximumSize];
    }

    public boolean isEmpty() {
        return (stackSize < 0);
    }

    public boolean isFull() {
        return (stackSize >= stackMaximumSize);
    }

    public int getSize() {
        return (stackSize + 1);
    }

    public void push(int x) throws FullStackException {
        if (isFull()) {
            throw new FullStackException();
        }
        stackSize++;
        stackContent[stackSize] = x;
    }

    public int pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int val = stackContent[stackSize];
        stackSize--;
        return val;
    }

    public int peek() {
        return stackContent[stackSize];
    }

    public boolean contains(Stack pile, int z) throws EmptyStackException {
        Stack pileCopy = pile;
        boolean app = false;
        while (!app) {
            int k = pileCopy.pop();
            if (k == z) {
                app = true;
            }
        }
        return app;
    }
}
