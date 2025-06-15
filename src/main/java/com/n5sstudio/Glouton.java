package com.n5sstudio;

import com.n5sstudio.exceptions.VertexOutboundLimitException;

class Glouton {
    private int[] couleur;
    private int[][] dSommet;
    private Graph g;
    private int nbCouleur;

    public Glouton(int[][] mat) {
        g = new Graph(mat);
        dSommet = new int[g.getMaximumNumberOfVertex()][6];
        couleur = new int[g.getMaximumNumberOfVertex()];
        nbCouleur = 0;
        for (int i = 0; i < g.getMaximumNumberOfVertex(); i++) {
            dSommet[i][0] = i;
            dSommet[i][1] = g.getVertexOutDegree(i);
        }
    }
    
    public void triDegreSommet() {
        for (int x = 0; x < g.getMaximumNumberOfVertex(); x++) {
            int i = 0;
            while ((dSommet[i][2] >= dSommet[x][2]) && (i < x)) {
                i++;
            }
            int[] tmp = new int[2];
            tmp[0] = dSommet[x][0];
            tmp[1] = dSommet[x][1];
            for (int y = x - 1; y >= i; y--) {
                dSommet[y + 1][0] = dSommet[y][0];
                dSommet[y + 1][1] = dSommet[y][1];
            }
            dSommet[i][0] = tmp[0];
            dSommet[i][1] = tmp[1];
        }
    }

    public int max(int i, int j) {
        if (i < j) {
            return j;
        } else {
            return i;
        }
    }

    public void coloration() throws VertexOutboundLimitException {
        for (int j = 0; j < g.getMaximumNumberOfVertex(); j++) {
            for (int i = 0; i < dSommet[j][1]; i++) {
                int tmp = g.getSuccessorList(dSommet[j][0])[i];
                if (couleur[tmp] == couleur[dSommet[j][0]]) {
                    couleur[tmp] = couleur[tmp] + 1;
                    nbCouleur = max(nbCouleur, couleur[tmp]);
                }
            }
        }
    }

    public int getNbCouleur() {
        return nbCouleur + 1;
    }

    public void afficheCouleur() {
        Stack[] sCouleur = new Stack[nbCouleur + 1];
        for (int i = 0; i < nbCouleur + 1; i++) {
            sCouleur[i] = new Stack(g.getMaximumNumberOfVertex());
            for (int j = 0; j < g.getMaximumNumberOfVertex(); j++) {
                if (couleur[j] == i) {
                    sCouleur[i].push(j);
                }
            }
        }
    }
}
