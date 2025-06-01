public class Graphe {

    private int[][] adjacencyMatrix;
    private int maximumNumberOfVertex;
    private boolean[] vertexExistanceArray;
    private static int UNDEFINED = 0;
    
    public Graphe() {
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
    
    public Graphe(int[][] adjacencyMatrix) {
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
                    adjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                } else {
                    adjacencyMatrix[i][j] = UNDEFINED;
                }
            }
        }
    }

    // CONSTRUCTEUR GRAPHE ; PARAM. NB MAXI DE SOMMETS.
    public Graphe(int maximumNumberOfVertex) {
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

    // CONSTRUCTEUR GRAPHE CLONE ; PARAM. GRAPHE.
    public Graphe(Graphe graph) {
        maximumNumberOfVertex = graph.maximumNumberOfVertex;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            vertexExistanceArray[i] = graph.vertexExistanceArray[i];
        }
    }

    // EXISTANCE D'UN SOMMET.
    public boolean hasVertex(int vertexIndex) {
        return vertexExistanceArray[vertexIndex];
    }

    // NOMBRE DE SOMMETS
    public int getVertexCount() {
        int cpt = 0;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            if (vertexExistanceArray[i])
                cpt++;
        }
        return cpt;
    }

    // AJOUTER UN SOMMET.
    public boolean addVertex(int vertexIndex) {
        if (vertexIndex < maximumNumberOfVertex && !hasVertex(vertexIndex)) {
            vertexExistanceArray[vertexIndex] = true;
            return true;
        } else {
            return false;
        }
    }

    // SUPPRIMER UN SOMMET.
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

    // EXISTANCE D'UN ARC.
    public boolean hasArc(int originVertexIndex, int destinationVertexIndex) {
        if (hasVertex(originVertexIndex) && hasVertex(destinationVertexIndex) && adjacencyMatrix[originVertexIndex][destinationVertexIndex] != UNDEFINED) {
            return true;
        } else {
            return false;
        }
    }

    // VALEUR D'UN ARC.
    public int getArcValue(int originVertexIndex, int destinationVertexIndex) {
        if (hasArc(originVertexIndex, destinationVertexIndex)) {
            return adjacencyMatrix[originVertexIndex][destinationVertexIndex];
        } else {
            return UNDEFINED;
        }
    }

    // AJOUTER UN ARC.
    public boolean addArc(int originVertexIndex, int destinationVertexIndex, int val) {
        if (hasVertex(originVertexIndex) && hasVertex(destinationVertexIndex) && adjacencyMatrix[originVertexIndex][destinationVertexIndex] == UNDEFINED) {
            adjacencyMatrix[originVertexIndex][destinationVertexIndex] = val;
            return true;
        } else {
            return false;
        }
    }

    // SUPPRIMER UN ARC.
    public boolean deleteArc(int originVertexIndex, int destinationVertexIndex) {
        if (hasArc(originVertexIndex, destinationVertexIndex)) {
            adjacencyMatrix[originVertexIndex][destinationVertexIndex] = UNDEFINED;
            return true;
        } else {
            return false;
        }
    }

    // DEGRE SORTANT
    public int getVertexOutDegree(int vertexIndex) {
        int degE = 0;
        for (int j = 0; j < maximumNumberOfVertex; j++) {
            if (adjacencyMatrix[vertexIndex][j] != UNDEFINED)
                degE++;
        }
        return degE;
    }

    // DEGRE ENTRANT
    public int getVertexInDegree(int vertexIndex) {
        int degS = 0;
        for (int j = 0; j < maximumNumberOfVertex; j++) {
            if (adjacencyMatrix[j][vertexIndex] != UNDEFINED)
                degS++;
        }
        return degS;
    }

    // DEGRE
    public int getVertexDegree(int vertexIndex) {
        return getVertexInDegree(vertexIndex) + getVertexOutDegree(vertexIndex);
    }

    // LISTE DES SUCCESSEURS.
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

    // LISTE DES PREDECESSEURS
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

    // toString
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

    // R�flexivit�
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

    // Anti-R�flexivit�
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

    // Sym�trie
    public boolean isSymmetric() {
        boolean Sym = true;
        for (int i = 0; i < maximumNumberOfVertex; i++) {
            for (int j = 0; j < maximumNumberOfVertex; j++) {
                if (adjacencyMatrix[i][j] != UNDEFINED && adjacencyMatrix[i][j] != UNDEFINED) {
                    Sym = Sym && true;
                } else {
                    Sym = false;
                }
            }
        }
        return Sym;
    }

    // Anti-Sym�trie
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

    // Transitivit�
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

    // Anti-Transitivit�
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

    // Transposition
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

    // Fin des Methodes

    // Fonction MAIN
    // On fait ici une s�rie de test permettant de v�rifier si le programme est
    // coh�rent
    public static void main(String[] args) {
        Graphe g = new Graphe(10); // On cr�e le graphe g pouvant disposer de 10 sommets au maximum
        System.out.println(g.hasVertex(2)); // FALSE, en effet le sommet 2 n'existe pas.
        System.out.println(g.addVertex(1)); // TRUE, le sommet 1 vient d'�tre cr�e
        System.out.println(g.addVertex(2)); // TRUE, le sommet 2 vient d'�tre cr�e
        System.out.println(g.addVertex(3)); // TRUE, le sommet 3 vient d'�tre cr�e
        System.out.println(g.hasVertex(1)); // TRUE, en effet le sommet 1 existe
        System.out.println(g.getVertexCount()); // 3, il y a bien 3 sommets dans le Graphe g
        System.out.println(g.hasArc(1, 3)); // FALSE, en effet l'arc n'existe pas
        System.out.println(g.getArcValue(1, 2)); // 0, 0 correspond � ALPHA_NOTDEF, l'arc n'existe pas
        System.out.println(g.addArc(1, 3, 5)); // TRUE, l'arc 1-3 vient d'�tre cr�e et a pour valeur 5
        System.out.println(g.addArc(1, 2, 3)); // TRUE, l'arc 1-2 vient d'�tre cr�e et a pour valeur 3
        System.out.println(g.getArcValue(1, 2)); // 3, l'arc 1-3 a pour valeur 3
        System.out.println(g.getArcValue(3, 1)); // 0, l'arc 3-1 n'existe pas !
        System.out.println(g.addVertex(4)); // TRUE, le sommet 4 vient d'�tre cr�e
        System.out.println(g.hasVertex(4)); // TRUE, en effet le sommet 4 existe
        System.out.println(g.deleteVertex(4)); // TRUE, le sommet 4 est supprim�
        System.out.println(g.hasVertex(4)); // FALSE, en effet le sommet 4 n'existe plus
        System.out.println(g.getVertexInDegree(1)); // 0 qui est bien le degr�s entrant de 1
        System.out.println(g.getVertexInDegree(2)); // 1 qui est bien le degr�s entrant de 2
        System.out.println(g.addArc(3, 1, 3)); // TRUE, l'arc 3-1 vient d'�tre cr�e et a pour valeur 3
        System.out.println(g.addArc(2, 3, 3)); // TRUE, l'arc 2-3 vient d'�tre cr�e et a pour valeur 3
        System.out.println(g.getVertexOutDegree(1)); // 2, qui est bien le dergr�s sortant de 1
        System.out.println(g.getVertexOutDegree(3)); // 1, qui est bien le dergr�s sortant de 3
        System.out.println(g.getVertexDegree(1)); // 3, qui est bien le dergr�s de 1
        System.out.println(g.getVertexDegree(3)); // 3, qui est bien le dergr�s de 3
        System.out.println(g.toString()); // 1 :23 ; 2 :3 ; 3 :1 (probl�me d'�criture s'il y a plusieurs sommets !!!)
        System.out.println(g.isReflexive()); // FALSE OK
        System.out.println(g.isIrreflexive()); // TRUE OK
        System.out.println(g.isSymmetric()); // FALSE OK
        System.out.println(g.isAntisymmetric()); // FALSE OK
        System.out.println(g.isTransitive()); // FALSE OK
        System.out.println(g.isAntiTransitive()); // FALSE OK
    }

    // Fin MAIN

}

// Fin Classe Graphe

/*
 * Notes Suppl�mentaires :
 * - Les Fonctions Union, Composition et Sousgraphe n'ont pas �t� compl�tement
 * termin�e et ne figurent pas dans le script ci-dessus.
 * - Tous les cas n'ont pas pu �tre trait� par manque de temps afin de v�rifier
 * correctement les fonctions de R�flexivit�, Sym�trie, Transitivit�.
 */
