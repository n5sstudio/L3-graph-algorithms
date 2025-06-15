package com.n5sstudio;

import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class WelshPowell {
    private int[] color;
    private int[][] dSommet;
    private Graph g;
    private int nbColor;

    public WelshPowell(int[][] mat) {
        g = new Graph(mat);
        dSommet = new int[g.getMaximumNumberOfVertex()][2];
        color = new int[g.getMaximumNumberOfVertex()];
        nbColor = 0;
        for (int i = 0; i < g.getMaximumNumberOfVertex(); i++) {
            dSommet[i][0] = i;
            dSommet[i][1] = g.getVertexOutDegree(i);
        }
    }

    public void triDegreSommet() {
        for (int x = 1; x < g.getMaximumNumberOfVertex(); x++) {
            int k = 0;
            while ((dSommet[k][1] >= dSommet[x][1]) && (k < x)) {
                k++;
            }
            int[] tmp = new int[2];
            tmp[0] = dSommet[x][0];
            tmp[1] = dSommet[x][1];
            for (int y = x - 1; y >= k; y--) {
                dSommet[y + 1][0] = dSommet[y][0];
                dSommet[y + 1][1] = dSommet[y][1];
            }
            dSommet[k][0] = tmp[0];
            dSommet[k][1] = tmp[1];
        }
    }

    public boolean possibleCouleur(int s, int c) throws VertexOutboundLimitException {
        boolean ok = true;
        int i = 0;
        while ((ok) && (i < g.getVertexOutDegree(s))) {
            ok = ((color[g.getSuccessorList(s)[i]]) != c);
            i++;
        }
        return ok;
    }

    public void coloration() throws VertexOutboundLimitException {
        triDegreSommet();
        int couvert = 0;
        for (int i = 0; i < g.getMaximumNumberOfVertex(); i++) {
            color[i] = nbColor;
        }
        while (couvert < g.getMaximumNumberOfVertex()) {
            nbColor++;
            for (int j = 0; j < g.getMaximumNumberOfVertex(); j++) {
                int s = dSommet[j][0];
                if ((color[s] == 0) && (possibleCouleur(s, nbColor))) {
                    color[s] = nbColor;
                    couvert++;
                }
            }
        }
    }

}
