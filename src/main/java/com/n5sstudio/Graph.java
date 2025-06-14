package com.n5sstudio;

import java.util.Arrays;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.NotImplementedException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Graph {

    private int[][] adjacencyMatrix;
    private int maximumNumberOfVertex;
    private boolean[] vertexExistanceArray;
    
    public static final int DEFAULT_NON_EXISTING_ARC_VALUE = 0;
    private static final int DEFAULT_MAXIMUM_NUMBER_OF_VERTEX = 1000;

    public Graph() {
        this.maximumNumberOfVertex = DEFAULT_MAXIMUM_NUMBER_OF_VERTEX;
        initGraph(this.maximumNumberOfVertex);
    }

    public Graph(int[][] adjacencyMatrix) {
        this.maximumNumberOfVertex = 2 * adjacencyMatrix.length;
        initGraph(this.maximumNumberOfVertex);

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if ((adjacencyMatrix[i][j] != DEFAULT_NON_EXISTING_ARC_VALUE)) {
                    this.vertexExistanceArray[i] = true;
                    this.vertexExistanceArray[j] = true;
                    this.adjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                }
            }
        }
    }

    public Graph(int maximumNumberOfVertex) {
        this.maximumNumberOfVertex = maximumNumberOfVertex;
        initGraph(this.maximumNumberOfVertex);
    }

    public Graph(Graph graph) throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        this.maximumNumberOfVertex = graph.maximumNumberOfVertex;
        initGraph(this.maximumNumberOfVertex);

        this.vertexExistanceArray = Arrays.copyOf(graph.vertexExistanceArray, graph.maximumNumberOfVertex);
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                this.adjacencyMatrix[i][j] = DEFAULT_NON_EXISTING_ARC_VALUE;
                if (graph.hasArc(i, j)) {
                    this.addArc(i, j, graph.getArcValue(i, j));
                }
            }
        }
    }

    private void initGraph(int maximumNumberOfVertex) {
        this.maximumNumberOfVertex = maximumNumberOfVertex;
        this.adjacencyMatrix = new int[this.maximumNumberOfVertex][this.maximumNumberOfVertex];
        this.vertexExistanceArray = new boolean[maximumNumberOfVertex];
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            this.vertexExistanceArray[i] = false;
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                this.adjacencyMatrix[i][j] = DEFAULT_NON_EXISTING_ARC_VALUE;
            }
        }
    }

    public int getUndefiledValue() {
        return Graph.DEFAULT_NON_EXISTING_ARC_VALUE;
    }

    public int getMaximumNumberOfVertex() {
        return this.maximumNumberOfVertex;
    }

    public int getVertexCount() {
        int nbVertices = 0;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            if (this.vertexExistanceArray[i])
                nbVertices++;
        }
        return nbVertices;
    }

    public boolean hasVertex(int vertexIndex) throws VertexOutboundLimitException {
        if (vertexIndex >= this.maximumNumberOfVertex || vertexIndex < 0) {
            throw new VertexOutboundLimitException();
        }
        return this.vertexExistanceArray[vertexIndex];
    }

    public void addVertex(int vertexIndex) throws VertexAlreadyExistsException, VertexOutboundLimitException {
        if (vertexIndex >= this.maximumNumberOfVertex) {
            throw new VertexOutboundLimitException();
        }
        if (hasVertex(vertexIndex)) {
            throw new VertexAlreadyExistsException();
        }
        this.vertexExistanceArray[vertexIndex] = true;
    }

    public boolean deleteVertex(int vertexIndex) throws VertexOutboundLimitException {
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

    public boolean hasArc(int originVertexIndex, int destinationVertexIndex) throws VertexOutboundLimitException {
        return ((this.hasVertex(originVertexIndex) && this.hasVertex(destinationVertexIndex))
                && this.adjacencyMatrix[originVertexIndex][destinationVertexIndex] != DEFAULT_NON_EXISTING_ARC_VALUE);
    }

    public int getArcValue(int originVertexIndex, int destinationVertexIndex) throws VertexOutboundLimitException {
        if (this.hasArc(originVertexIndex, destinationVertexIndex)) {
            return this.adjacencyMatrix[originVertexIndex][destinationVertexIndex];
        } else {
            return DEFAULT_NON_EXISTING_ARC_VALUE;
        }
    }

    public void addArc(int originVertexIndex, int destinationVertexIndex, int arcValue) throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        if (this.hasArc(originVertexIndex, destinationVertexIndex)) {
            throw new ArcAlreadyExistsException();
        }
        if (this.hasVertex(originVertexIndex) && this.hasVertex(destinationVertexIndex)) {
            this.adjacencyMatrix[originVertexIndex][destinationVertexIndex] = arcValue;
        } else {
            throw new VertexDoesNotExistsException();
        }
    }

    public boolean deleteArc(int originVertexIndex, int destinationVertexIndex) throws VertexOutboundLimitException {
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

    public boolean[] getSuccessorBooleanList(int vertexIndex) throws VertexOutboundLimitException {
        boolean[] list = new boolean[maximumNumberOfVertex];
        for (int j = 0; j < this.maximumNumberOfVertex; j++) {
            if (this.hasArc(vertexIndex, j)) {
                list[j] = true;
            } else {
                list[j] = false;
            }
        }
        return list;
    }

    public boolean[] getPredecessorBooleanList(int vertexIndex) throws VertexOutboundLimitException {
        boolean[] list = new boolean[maximumNumberOfVertex];
        for (int j = 0; j < this.maximumNumberOfVertex; j++) {
            if (this.hasArc(j, vertexIndex)) {
                list[j] = true;
            } else {
                list[j] = false;
            }
        }
        return list;
    }

    public boolean isReflexive() throws VertexOutboundLimitException {
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            if (this.hasVertex(i) && !this.hasArc(i, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isIrreflexive() throws VertexOutboundLimitException {
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            if (this.hasArc(i, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSymmetric() throws VertexOutboundLimitException {
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            for (int j = i; j < this.maximumNumberOfVertex; j++) {
                if (this.hasArc(i, j) && !this.hasArc(j, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isAntisymmetric() throws VertexOutboundLimitException {
        for (int i = 0; i < this.maximumNumberOfVertex; i++) {
            for (int j = i + 1; j < this.maximumNumberOfVertex; j++) {
                if (this.hasArc(i, j) && this.hasArc(j, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTransitive() throws VertexOutboundLimitException {
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

    public boolean isAntiTransitive() throws VertexOutboundLimitException {
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
            for (int j = i + 1; j < this.maximumNumberOfVertex; j++) {
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