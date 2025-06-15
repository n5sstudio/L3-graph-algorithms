package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Prim {

    private int[][] lstAreteTriee;
    private Graph g;
    private int poids;
    private int maxNumberOfVertex;

    public Prim(int[][] mat) {
        g = new Graph(mat);
        maxNumberOfVertex = mat.length;
        poids = 0;
        lstAreteTriee = new int[maxNumberOfVertex * maxNumberOfVertex][2];
    }
    
    public void trieArete() throws VertexOutboundLimitException {
        int tmp1;
        int tmp2;
        int k1 = 0;
        for (int i = 0; i < maxNumberOfVertex; i++) {
            for (int j = 0; j < maxNumberOfVertex; j++) {
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

    private int[] choixAreteMin(Graph tree) throws VertexOutboundLimitException {
        int i = -1;
        boolean test = true;
        int[] tab = new int[2];
        while (test) {
            i++;
            if ((tree.hasVertex(lstAreteTriee[i][0]) &&
                    !tree.hasVertex(lstAreteTriee[i][1]))
                    || (!tree.hasVertex(lstAreteTriee[i][0]) &&
                            tree.hasVertex(lstAreteTriee[i][1]))) {
                test = false;
            }

        }
        tab[0] = lstAreteTriee[i][0];
        tab[1] = lstAreteTriee[i][1];
        return tab;
    }

    public Graph fPrim() throws VertexOutboundLimitException, VertexAlreadyExistsException, ArcAlreadyExistsException,
            VertexDoesNotExistsException {
        int x;
        int y;
        Graph tree = new Graph();
        trieArete();
        tree.addVertex(5);

        while (tree.getVertexCount() < maxNumberOfVertex) {
            x = choixAreteMin(tree)[0];
            y = choixAreteMin(tree)[1];
            if (tree.hasVertex(x)) {
                tree.addVertex(y);
            } else {
                tree.addVertex(x);
            }
            tree.addArc(x, y, g.getArcValue(x, y));
            poids = poids + g.getArcValue(x, y);
        }
        return tree;
    }

    public int getPoids() {
        return poids;
    }
}
