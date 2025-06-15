package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Prim {

    private int[][] lstAreteTriee;
    private Graph g;
    private int poids;
    private int N;

    public Prim(int[][] mat) {
        int t = 0;
        g = new Graph(mat);
        N = mat.length;
        poids = 0;
        lstAreteTriee = new int[N * N][2];
    }

    /* Trie les Arc en fonction de leur valeur */
    public void trieArete() throws VertexOutboundLimitException {
        int tmp1;
        int tmp2;
        int k1 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (g.hasArc(i, j)) {
                    lstAreteTriee[k1][0] = i;
                    lstAreteTriee[k1][1] = j;
                    k1++;
                }
            }
        }
        for (int i = 0; i < k1 - 1; i++) {
            for (int j = 0; j < k1 - i - 1; j++) {
                if (g.getArcValue(lstAreteTriee[j][0], lstAreteTriee[j][1]) > g.getArcValue(lstAreteTriee[j + 1][0],
                        lstAreteTriee[j + 1][1])) {
                    tmp1 = lstAreteTriee[j + 1][0];
                    tmp2 = lstAreteTriee[j + 1][1];
                    lstAreteTriee[j + 1][0] = lstAreteTriee[j][0];
                    lstAreteTriee[j + 1][1] = lstAreteTriee[j][1];
                    lstAreteTriee[j][0] = tmp1;
                    lstAreteTriee[j][1] = tmp2;
                }
            }
        }
    }
    
    private int[] choixAreteMin(Graph A) throws VertexOutboundLimitException {
        int i = -1;
        boolean test = true;
        int[] tab = new int[2];
        while (test == true) {
            i++;
            if ((A.hasVertex(lstAreteTriee[i][0]) == true) &&
                    (A.hasVertex(lstAreteTriee[i][1]) == false)) {
                test = false;
            }
            if ((A.hasVertex(lstAreteTriee[i][0]) == false) &&
                    (A.hasVertex(lstAreteTriee[i][1]) == true)) {
                test = false;
            }

        }
        tab[0] = lstAreteTriee[i][0];
        tab[1] = lstAreteTriee[i][1];
        return tab;
    }
    
    private Graph fPrim() throws VertexOutboundLimitException, VertexAlreadyExistsException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        int x;
        int y;
        int k = 0;
        Graph A = new Graph();
        trieArete();
        A.addVertex(5);

        while (A.getMaximumNumberOfVertex() < N) {
            k++;
            x = choixAreteMin(A)[0];
            y = choixAreteMin(A)[1];
            if (A.hasVertex(x)) {
                A.addVertex(y);
            } else {
                A.addVertex(x);
            }
            A.addArc(x, y, g.getArcValue(x, y));
            poids = poids + g.getArcValue(x, y);
        }
        return A;
    }
    
    public int getPoids() {
        return poids;
    }
}
