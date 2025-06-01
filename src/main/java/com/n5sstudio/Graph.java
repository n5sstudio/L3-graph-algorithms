package com.n5sstudio;

import java.util.Arrays;

import com.n5sstudio.exceptions.NotImplementedException;

public class Graph {

    private int[][] adjacencyMatrix;
    private int maximumNumberOfVertex;
    private boolean[] vertexExistanceArray;
    
    private static final int DEFAULT_NON_EXISTING_ARC_VALUE = 0;
    private static final int DEFAULT_MAXIMUM_NUMBER_OF_VERTEX = 1000;

    public Graph() {
        this.maximumNumberOfVertex = DEFAULT_MAXIMUM_NUMBER_OF_VERTEX;
        this.adjacencyMatrix = new int[this.maximumNumberOfVertex][this.maximumNumberOfVertex];
        this.vertexExistanceArray = new boolean[maximumNumberOfVertex];
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            this.vertexExistanceArray[i] = false;
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                this.adjacencyMatrix[i][j] = DEFAULT_NON_EXISTING_ARC_VALUE;
            }
        }
    }

    public Graph(int[][] adjacencyMatrix) {
        this.maximumNumberOfVertex = 2 * adjacencyMatrix.length;
        this.adjacencyMatrix = new int[this.maximumNumberOfVertex][this.maximumNumberOfVertex];
        this.vertexExistanceArray = new boolean[this.maximumNumberOfVertex];
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            if (i < adjacencyMatrix.length) {
                this.vertexExistanceArray[i] = true;
            } else {
                this.vertexExistanceArray[i] = false;
            }
            for (int j = 0; j < this.maximumNumberOfVertex; j++) {
                if ((i < adjacencyMatrix.length) && (j < adjacencyMatrix.length)) {
                    this.adjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                } else {
                    this.adjacencyMatrix[i][j] = DEFAULT_NON_EXISTING_ARC_VALUE;
                }
            }
        }
    }

    public Graph(int maximumNumberOfVertex) {
        this.maximumNumberOfVertex = maximumNumberOfVertex;
        this.adjacencyMatrix = new int[maximumNumberOfVertex][maximumNumberOfVertex];
        this.vertexExistanceArray = new boolean[maximumNumberOfVertex];
        for (int k = 0; k < maximumNumberOfVertex; k++) {
            this.vertexExistanceArray[k] = false;
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                this.adjacencyMatrix[k][j] = DEFAULT_NON_EXISTING_ARC_VALUE;
            }
        }
    }

    public Graph(Graph graph) {
        this.maximumNumberOfVertex = graph.maximumNumberOfVertex;
        this.vertexExistanceArray = Arrays.copyOf(graph.vertexExistanceArray, graph.maximumNumberOfVertex);
    }

    public int getUndefiledValue() {
        return Graph.DEFAULT_NON_EXISTING_ARC_VALUE;
    }

    public boolean hasVertex(int vertexIndex) {
        return this.vertexExistanceArray[vertexIndex];
    }

    public int getVertexCount() {
        int cpt = 0;
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            if (this.vertexExistanceArray[i])
                cpt++;
        }
        return cpt;
    }

    public boolean addVertex(int vertexIndex) {
        if (vertexIndex < this.maximumNumberOfVertex && !hasVertex(vertexIndex)) {
            this.vertexExistanceArray[vertexIndex] = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteVertex(int vertexIndex) {
        if (!this.hasVertex(vertexIndex)) {
            return false;
        } else {
            for (int j = 0; j < this.maximumNumberOfVertex; j++) {
                this.adjacencyMatrix[vertexIndex][j] = DEFAULT_NON_EXISTING_ARC_VALUE;
                this.adjacencyMatrix[j][vertexIndex] = DEFAULT_NON_EXISTING_ARC_VALUE;
            }
            this.vertexExistanceArray[vertexIndex] = false;
            return true;
        }
    }

    public boolean hasArc(int originVertexIndex, int destinationVertexIndex) {
        return (this.hasVertex(originVertexIndex) && this.hasVertex(destinationVertexIndex)
                && this.adjacencyMatrix[originVertexIndex][destinationVertexIndex] != DEFAULT_NON_EXISTING_ARC_VALUE);
    }

    public int getArcValue(int originVertexIndex, int destinationVertexIndex) {
        if (this.hasArc(originVertexIndex, destinationVertexIndex)) {
            return this.adjacencyMatrix[originVertexIndex][destinationVertexIndex];
        } else {
            return DEFAULT_NON_EXISTING_ARC_VALUE;
        }
    }

    public boolean addArc(int originVertexIndex, int destinationVertexIndex, int arcValue) {
        if (!this.hasArc(originVertexIndex, destinationVertexIndex)) {
            this.adjacencyMatrix[originVertexIndex][destinationVertexIndex] = arcValue;
            return true;
        }
        return false;
    }

    public boolean deleteArc(int originVertexIndex, int destinationVertexIndex) {
        if (this.hasArc(originVertexIndex, destinationVertexIndex)) {
            this.adjacencyMatrix[originVertexIndex][destinationVertexIndex] = DEFAULT_NON_EXISTING_ARC_VALUE;
            return true;
        } else {
            return false;
        }
    }

    public int getVertexOutDegree(int vertexIndex) {
        int outDegree = 0;
        for (int j = 0; j < this.maximumNumberOfVertex; j++) {
            if (this.adjacencyMatrix[vertexIndex][j] != DEFAULT_NON_EXISTING_ARC_VALUE)
                outDegree++;
        }
        return outDegree;
    }

    public int getVertexInDegree(int vertexIndex) {
        int inDegree = 0;
        for (int j = 0; j < this.maximumNumberOfVertex; j++) {
            if (this.adjacencyMatrix[j][vertexIndex] != DEFAULT_NON_EXISTING_ARC_VALUE)
                inDegree++;
        }
        return inDegree;
    }

    public int getVertexDegree(int vertexIndex) {
        return this.getVertexInDegree(vertexIndex) + this.getVertexOutDegree(vertexIndex);
    }

    public int[] getSuccessorList(int vertexIndex) {
        int[] liste = new int[maximumNumberOfVertex];
        int k = 0;
        for (int j = 0; j < this.maximumNumberOfVertex; j++) {
            if (this.hasArc(vertexIndex, j)) {
                liste[k] = j;
                k++;
            } else {
                liste[k] = DEFAULT_NON_EXISTING_ARC_VALUE;
            }
        }
        return liste;
    }

    public int[] getPredecessorList(int vertexIndex) {
        int[] liste = new int[maximumNumberOfVertex];
        int k = 0;
        for (int j = 0; j < this.maximumNumberOfVertex; j++) {
            if (this.hasArc(j, vertexIndex)) {
                liste[k] = j;
                k++;
            } else {
                liste[k] = DEFAULT_NON_EXISTING_ARC_VALUE;
            }
        }
        return liste;
    }

    public boolean isReflexive() {
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            if (this.adjacencyMatrix[i][i] == DEFAULT_NON_EXISTING_ARC_VALUE) {
                return false;
            }
        }
        return true;
    }

    public boolean isIrreflexive() {
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            if (this.adjacencyMatrix[i][i] != DEFAULT_NON_EXISTING_ARC_VALUE) {
                return false;
            }
        }
        return true;
    }

    public boolean isSymmetric() {
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            for (int j = i; j < this.maximumNumberOfVertex; j++) {
                if (this.adjacencyMatrix[i][j] != this.adjacencyMatrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isAntisymmetric() {
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            for (int j = i + 1; j < this.maximumNumberOfVertex; j++) {
                if (this.adjacencyMatrix[i][j] == this.adjacencyMatrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTransitive() {
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            for (int j = 0; j < this.maximumNumberOfVertex; j++) {
                for (int k = 0; k < this.maximumNumberOfVertex; k++) {
                    if ((this.hasArc(i, j) && this.hasArc(j, k)) && !this.hasArc(i, k)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isAntiTransitive() {
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            for (int j = 0; j < this.maximumNumberOfVertex; j++) {
                for (int k = 0; k < this.maximumNumberOfVertex; k++) {
                    if ((this.hasArc(i, j) && this.hasArc(j, k)) && this.hasArc(i, k)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void transpose() {
        int tmp;
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            for (int j = 0; j < this.maximumNumberOfVertex; j++) {
                tmp = this.adjacencyMatrix[i][j];
                this.adjacencyMatrix[i][j] = this.adjacencyMatrix[j][i];
                this.adjacencyMatrix[j][i] = tmp;
            }
        }
    }

    public void union(Graph g, Graph h) throws NotImplementedException {
        throw new NotImplementedException();
    }

    public void composition(Graph g, Graph h) throws NotImplementedException {
        throw new NotImplementedException();
    }

    public void subgraph(Graph g, Graph h) throws NotImplementedException {
        throw new NotImplementedException();
    }

}