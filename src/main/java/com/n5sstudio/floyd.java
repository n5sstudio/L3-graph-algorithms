package com.n5sstudio;

public class floyd {
    private Graphe g;
    private int depart;
    private int[] D;
    private int[][] R;
    private boolean[] S;
    private int INFINI = 1000000;
    private static int ALPHA_NOTDEF = 1000;

    public floyd(int depart0, Graphe g0) {
        depart = depart0;
        g = new Graphe(g0);
        D = new int[g.nbSommet()];
        S = new boolean[g.nbSommet()];
    }

    public void initRoutage() {
        for (int x = 0; x < g.nbSommet(); x++) {
            for (int y = 0; y < g.nbSommet(); y++) {
                if (g.existeArc(x, y)) {
                    R[x][y] = x;
                } else {
                    R[x][y] = ALPHA_NOTDEF;
                }
            }
        }
    }

    public void PlusCourtCheminWk() {
        Graphe g2 = new Graphe(g);
        int v1;
        int v2;
        int w;
        initRoutage();
        for (int x = 0; x < g2.nbSommet(); x++) {
            for (int y = 0; y < g2.nbSommet(); y++) {
                v1 = g2.getValArc(y, x);
                if (v1 != ALPHA_NOTDEF) {
                    for (int z = 0; z < g2.nbSommet(); z++) {
                        v2 = g2.getValArc(x, z);
                        if (v2 != ALPHA_NOTDEF) {
                            w = g2.getValArc(y, z);
                            if (w != ALPHA_NOTDEF) {
                                if ((v1 + v2) < w) {
                                    g2.modifArc(y, z, v1 + v2);
                                    R[y][z] = R[x][z];
                                } else {
                                    g2.ajoutArc(y, z, v1 + v2);
                                    R[y][z] = R[x][z];
                                }
                            }
                        }
                    }
                }
                System.out.print(R[x][y] + " ");
            }
            System.out.println();
        }

    }

    public void AfficheRout() {
        // Routage
        System.out.println("Routage");
        System.out.println();

        for (int i = 0; i < g.nbSommet(); i++) {
            for (int j = 0; j < g.nbSommet(); j++) {
                System.out.print(R[i][j] + " ");
            }
            System.out.println();
        }
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

        floyd F = new floyd(0, g);
    }
}
