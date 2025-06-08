package com.n5sstudio;

import java.util.Arrays;

import com.n5sstudio.exceptions.EmptyStackException;
import com.n5sstudio.exceptions.FullStackException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Path {

    private Graph g;
    
    public Path(Graph g0) {
        g = g0;
    }
    
    public int getSize(int[] tab) {
        return tab[tab.length - 1];
    }
    
    public int[] removeLastElement(int[] tab) {
        return Arrays.copyOf(tab, tab.length - 1);
    }
    
    public void afficheChemin(int depart, int arrivee) throws VertexOutboundLimitException, EmptyStackException, FullStackException {
        Stack p = new Stack();
        int[][] vertexIdToVisitArray = new int[g.getMaximumNumberOfVertex()][];
        int curentVertexId;
        int vertexIdToVisit;
        vertexIdToVisitArray[depart] = g.getSuccessorList(depart);
        if (vertexIdToVisitArray[depart].length > 0) {
            p.push(depart);
        }
        while (!p.isEmpty()) {
            curentVertexId = p.peek();
            if (vertexIdToVisitArray[curentVertexId].length > 0) {
                vertexIdToVisit = vertexIdToVisitArray[curentVertexId][vertexIdToVisitArray[depart].length - 1];
                removeLastElement(vertexIdToVisitArray[curentVertexId]);
                if (!p.contains(p, vertexIdToVisit) && vertexIdToVisit != arrivee) {
                    vertexIdToVisitArray[vertexIdToVisit] = g.getSuccessorList(vertexIdToVisit);
                }
                if (!p.contains(p, vertexIdToVisit) && vertexIdToVisitArray[vertexIdToVisit].length > 0) {
                    p.push(vertexIdToVisit);
                }
            } else {
                p.pop();
            }
        }
    }
}
