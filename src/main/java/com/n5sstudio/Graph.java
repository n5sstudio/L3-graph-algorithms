package com.n5sstudio;

public class Graph {

    private int[][] adjacencyMatrix;
    private int maximumNumberOfVertex;
    private boolean[] vertexExistanceArray;
    private static int UNDEFINED = 0;

    public Graph() {
        maximumNumberOfVertex = 1000;
        adjacencyMatrix = new int[maximumNumberOfVertex][maximumNumberOfVertex];
        vertexExistanceArray = new boolean[maximumNumberOfVertex];
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            vertexExistanceArray[i] = false;
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                adjacencyMatrix[i][j] = UNDEFINED;
            }
        }
    }

    public Graph(int[][] adjacencyMatrix) {
        maximumNumberOfVertex = 2 * adjacencyMatrix.length;
        adjacencyMatrix = new int[maximumNumberOfVertex][maximumNumberOfVertex];
        vertexExistanceArray = new boolean[maximumNumberOfVertex];
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            if (i < adjacencyMatrix.length) {
                vertexExistanceArray[i] = true;
            } else {
                vertexExistanceArray[i] = false;
            }
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                if ((i < adjacencyMatrix.length) && (j < adjacencyMatrix.length)) {
                    this.adjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                } else {
                    adjacencyMatrix[i][j] = UNDEFINED;
                }
            }
        }
    }

    public Graph(int maximumNumberOfVertex) {
        this.maximumNumberOfVertex = maximumNumberOfVertex;
        adjacencyMatrix = new int[maximumNumberOfVertex][maximumNumberOfVertex];
        vertexExistanceArray = new boolean[maximumNumberOfVertex];
        for (int k = 0; k < maximumNumberOfVertex; k++) {
            vertexExistanceArray[k] = false;
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                adjacencyMatrix[k][j] = UNDEFINED;
            }
        }
    }

    public Graph(Graph graph) {
        maximumNumberOfVertex = graph.maximumNumberOfVertex;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            vertexExistanceArray[i] = graph.vertexExistanceArray[i];
        }
    }

    public int getUndefiledValue() {
        return Graph.UNDEFINED;
    }

    public boolean hasVertex(int vertexIndex) {
        return vertexExistanceArray[vertexIndex];
    }

    public int getVertexCount() {
        int cpt = 0;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            if (vertexExistanceArray[i])
                cpt++;
        }
        return cpt;
    }

    public boolean addVertex(int vertexIndex) {
        if (vertexIndex < maximumNumberOfVertex && !hasVertex(vertexIndex)) {
            vertexExistanceArray[vertexIndex] = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteVertex(int vertexIndex) {
        if (!hasVertex(vertexIndex)) {
            return false;
        } else {
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                adjacencyMatrix[vertexIndex][j] = UNDEFINED;
                adjacencyMatrix[j][vertexIndex] = UNDEFINED;
            }
            vertexExistanceArray[vertexIndex] = false;
            return true;
        }
    }

    public boolean hasArc(int originVertexIndex, int destinationVertexIndex) {
        if (hasVertex(originVertexIndex) && hasVertex(destinationVertexIndex)
                && adjacencyMatrix[originVertexIndex][destinationVertexIndex] != UNDEFINED) {
            return true;
        } else {
            return false;
        }
    }

    public int getArcValue(int originVertexIndex, int destinationVertexIndex) {
        if (hasArc(originVertexIndex, destinationVertexIndex)) {
            return adjacencyMatrix[originVertexIndex][destinationVertexIndex];
        } else {
            return UNDEFINED;
        }
    }

    public boolean addArc(int originVertexIndex, int destinationVertexIndex, int val) {
        if (hasVertex(originVertexIndex) && hasVertex(destinationVertexIndex)
                && adjacencyMatrix[originVertexIndex][destinationVertexIndex] == UNDEFINED) {
            adjacencyMatrix[originVertexIndex][destinationVertexIndex] = val;
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteArc(int originVertexIndex, int destinationVertexIndex) {
        if (hasArc(originVertexIndex, destinationVertexIndex)) {
            adjacencyMatrix[originVertexIndex][destinationVertexIndex] = UNDEFINED;
            return true;
        } else {
            return false;
        }
    }

    public int getVertexOutDegree(int vertexIndex) {
        int degE = 0;
        for (int j = 0; j < maximumNumberOfVertex; j++) {
            if (adjacencyMatrix[vertexIndex][j] != UNDEFINED)
                degE++;
        }
        return degE;
    }

    public int getVertexInDegree(int vertexIndex) {
        int degS = 0;
        for (int j = 0; j < maximumNumberOfVertex; j++) {
            if (adjacencyMatrix[j][vertexIndex] != UNDEFINED)
                degS++;
        }
        return degS;
    }

    public int getVertexDegree(int vertexIndex) {
        return getVertexInDegree(vertexIndex) + getVertexOutDegree(vertexIndex);
    }

    public int[] getSuccessorList(int vertexIndex) {
        int[] liste = new int[maximumNumberOfVertex];
        int k = 0;
        for (int j = 0; j < maximumNumberOfVertex; j++) {
            if (hasArc(vertexIndex, j)) {
                liste[k] = j;
                k++;
            } else {
                liste[k] = UNDEFINED;
            }
        }
        return liste;
    }

    public int[] getPredecessorList(int vertexIndex) {
        int[] liste = new int[maximumNumberOfVertex];
        int k = 0;
        for (int j = 0; j < maximumNumberOfVertex; j++) {
            if (hasVertex(vertexIndex) && hasVertex(j) && adjacencyMatrix[j][vertexIndex] != UNDEFINED) {
                liste[k] = j;
                k++;
            } else {
                liste[k] = UNDEFINED;
            }
        }
        return liste;
    }

    public boolean isReflexive() {
        boolean Refl = true;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            if (adjacencyMatrix[i][i] != UNDEFINED) {
                Refl = Refl && true;
            } else {
                Refl = false;
            }
        }
        return Refl;
    }

    public boolean isIrreflexive() {
        boolean ARefl = true;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            if (adjacencyMatrix[i][i] != UNDEFINED) {
                ARefl = false;
            } else {
                ARefl = ARefl && true;
            }
        }
        return ARefl;
    }

    public boolean isSymmetric() {
        boolean Sym = true;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                if (adjacencyMatrix[i][j] != UNDEFINED && adjacencyMatrix[j][i] != UNDEFINED) {
                    Sym = Sym && true;
                } else {
                    Sym = false;
                }
            }
        }
        return Sym;
    }

    public boolean isAntisymmetric() {
        boolean ASym = true;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                if (i != j && adjacencyMatrix[i][j] == UNDEFINED && adjacencyMatrix[i][j] != UNDEFINED) {
                    ASym = ASym && true;
                } else {
                    ASym = false;
                }
            }
        }
        return ASym;
    }

    public boolean isTransitive() {
        boolean Trans = true;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                for (int k = 0; k < maximumNumberOfVertex; k++) {
                    if (hasArc(i, j) && hasArc(j, k) && hasArc(i, k)) {
                        Trans = Trans && true;
                    } else {
                        Trans = false;
                    }
                }
            }
        }
        return Trans;
    }

    public boolean isAntiTransitive() {
        boolean ATrans = true;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                for (int k = 0; k < maximumNumberOfVertex; k++) {
                    if (hasArc(i, j) && hasArc(j, k) && hasArc(i, k) == false) {
                        ATrans = ATrans && true;
                    } else {
                        ATrans = false;
                    }
                }
            }
        }
        return ATrans;
    }

    public void transpose() {
        int tmp;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                tmp = adjacencyMatrix[i][j];
                adjacencyMatrix[i][j] = adjacencyMatrix[j][i];
                adjacencyMatrix[j][i] = tmp;
            }
        }
    }

    public void union(Graph g, Graph h) {
    }

    public void composition(Graph g, Graph h) {
    }

    public void subgraph(Graph g, Graph h) {

    }

    public java.lang.String toString() {
        String ch = "";
        int[] lst_sommet;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            if (hasVertex(i)) {
                lst_sommet = getSuccessorList(i);
                ch = ch + i + " :";
                for (int j = 0; j < getVertexOutDegree(i); j++) {
                    ch = ch + lst_sommet[j];
                }
                ch = ch + "\n";
            }
        }
        return ch;
    }

}