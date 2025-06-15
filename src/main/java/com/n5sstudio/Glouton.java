package com.n5sstudio;

class Glouton {
    private int[] couleur;
    private int[][] dSommet;
    private Graph g;
    private int nbCouleur;

    public Glouton(int[][] mat) {
        g = new Graph(mat);
        dSommet = new int[g.nbsommet()][6];
        couleur = new int[g.nbsommet()];
        nbCouleur = 0;
        for (int i = 0; i < g.nbsommet(); i++) {
            dSommet[i][0] = i;
            dSommet[i][1] = g.degresortant(i);
        }
    }

    /* On trie les sommets par nombres de successeurs d�croissant */
    public void triDegreSommet() {
        for (int x = 0; x < g.nbsommet(); x++) {
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

    /* Fonction Minimum */
    public int max(int i, int j) {
        if (i < j) {
            return j;
        } else {
            return i;
        }
    }

    /* Coloration */
    public void coloration() {
        for (int j = 0; j < g.nbsommet(); j++) {
            for (int i = 0; i < dSommet[j][1]; i++) {
                int tmp = g.lst_succ(dSommet[j][0])[i];
                if (couleur[tmp] == couleur[dSommet[j][0]]) {
                    couleur[tmp] = couleur[tmp] + 1;
                    nbCouleur = max(nbCouleur, couleur[tmp]);
                }
            }
        }
    }

    /* Retourne le nombre de couleur */
    public int getNbCouleur() {
        return nbCouleur + 1;
    }

    /* affichage de la coloration */
    public void afficheCouleur() {
        Stack[] sCouleur = new Stack[nbCouleur + 1];
        for (int i = 0; i < nbCouleur + 1; i++) {
            sCouleur[i] = new Stack(g.nbsommet());
            for (int j = 0; j < g.nbsommet(); j++) {
                if (couleur[j] == i) {
                    sCouleur[i].empiler(j);
                }
            }
            System.out.println("Couleur " + (i + 1) + " - Sommets : ");
            System.out.println(sCouleur[i].toString());
        }
    }
}
