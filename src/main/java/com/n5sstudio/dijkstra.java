package com.n5sstudio;

public class dijkstra {
    private Graphe g;
    private int depart;
    private int[] D;
    private int[] R;
    private boolean[] S;
    private int INFINI = 1000000;

    public dijkstra(int depart0, Graphe g0) {
        depart = depart0;
        g = new Graphe(g0);
        D = new int[g.nbSommet()];
        S = new boolean[g.nbSommet()];
    }

    public int distanceDansGraphe(int i, int j) { // fonction distance entre 2 sommets
        if (g.existeArc(i, j) == true) {
            return g.U[i][j]; // retourne la valeur de l_arc
        } else {
            return INFINI; // retourne INFINI si l_arc n_existe pas
        }
    }

    public int[] initDistMin() {
        for (int i = 0; i < g.nbSommet(); i++) {
            D[i] = distanceDansGraphe(depart, i);
            R[i] = depart;
        }
        return D;
    }

    public void ajouter(int j) {
        if (!S[j]) {
            S[j] = true;
        }
    }

    public boolean appartient(int j) {
        return S[j];
    }

    public int choixSommet() {
        int min = INFINI;
        int k = 0;
        for (int i = 0; i < g.nbSommet(); i++) {
            if (D[i] < min && !appartient(i)) {
                min = D[i];
                k = i;
            }
        }
        return k;
    }

    public void AfficheDist() {
        // Affiche tout ce qui nous interesse !
        System.out.println("Distance Minimale");
        System.out.println();

        for (int i = 0; i < g.nbSommet(); i++) {
            System.out.print(D[i] + " ");
        }

        System.out.println();
    }

    public void AfficheRout() {
        // Routage histoire de rire !
        System.out.println("Routage");
        System.out.println();

        for (int i = 0; i < g.nbSommet(); i++) {
            System.out.print(R[i] + " ");
        }

        System.out.println();
    }

    public static void main(java.lang.String[] args) {
        int N = 11;
        Graphe g = new Graphe(N);

        for (int i = 0; i < N; i++) {
            g.ajoutSommet(i);
        }

        g.ajoutArc(0, 1, 4);
        g.ajoutArc(0, 6, 3);
        g.ajoutArc(0, 3, 11);
        g.ajoutArc(0, 9, 10);
        g.ajoutArc(3, 9, 2);
        g.ajoutArc(4, 3, 2);
        g.ajoutArc(4, 7, 2);
        g.ajoutArc(4, 8, 5);
        g.ajoutArc(6, 4, 5);
        g.ajoutArc(6, 10, 5);
        g.ajoutArc(7, 10, 6);
        g.ajoutArc(7, 4, 2);
        g.ajoutArc(8, 5, 6);
        g.ajoutArc(9, 2, 4);
        g.ajoutArc(9, 8, 5);
        g.ajoutArc(10, 7, 6);

        // Affichage matrice
        System.out.println("matrice d'adjacence :\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(g.matriceU()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        dijkstra K = new dijkstra(0, g);
        K.AfficheDist();

    }
}
