package com.n5sstudio;

public class Arc {

    private int origin;
    private int destination;
    private int value;

    public Arc(int origin, int destination, int value) {
        this.origin = origin;
        this.destination = destination;
        this.value = value;
    }

    public Arc(Arc referenceArc) {
        this.origin = referenceArc.getOrigin();
        this.destination = referenceArc.getDestination();
        this.value = referenceArc.getValue();
    }

    public int getOrigin() {
        return origin;
    }

    public int getDestination() {
        return destination;
    }

    public int getValue() {
        return value;
    }

}
