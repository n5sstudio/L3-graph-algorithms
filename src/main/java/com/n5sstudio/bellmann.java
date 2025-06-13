package com.n5sstudio;
public class bellmann {
  private Graphe g;
  private int depart;
  private int[] D;
  private int[] R;
  private boolean[] S;
  private int INFINI = 1000000;
  private boolean stable;

  public bellmann(int depart0, Graphe g0) {
    depart = depart0;
    g = new Graphe(g0);
    D = new int[g.nbSommet()];
    S = new boolean[g.nbSommet()];
    R = new int[g.nbSommet()];
  }

  public int[] initDistMin() {
    for (int i = 0; i < g.nbSommet(); i++) {
      D[i] = distanceDansGraphe(depart, i);
      R[i] = depart;
    }
    return D;
  }

  public int distanceDansGraphe(int i, int j) { //fonction distance entre 2 sommets
    if (g.existeArc(i, j) == true) {
      return g.U[i][j]; //retourne la valeur de l_arc
    }
    else {
      return INFINI; //retourne INFINI si l_arc n_existe pas
    }
  }

  public void plusCourtCheminBk() {
    initDistMin();
    int k = 1;
    stable = false;
    int cout;
    while (!stable && k <= g.nbSommet()) {
      int[] D_anc = new int[g.nbSommet()];
      k = k + 1;
      stable = true;
      D_anc = D;
      for (int i = 0; i < g.nbSommet(); i++) {
        for (int j = 0; j < g.nbSommet(); j++) {
          cout = D_anc[j] + g.getValArc(j, i);
          if (cout < D[i]) {
            D[i] = cout;
            R[i] = j;
            stable = false;
          }
        }
      }
    }
  }

  public void AfficheDist() {
// Affiche Distance Minimale
    System.out.println("Distance Minimale");
    System.out.println();

    for (int i = 1; i < g.nbSommet(); i++) {
      System.out.print(D[i] + " ");
    }
    System.out.println();
    System.out.println();
  }

  public void AfficheRout() {
// Routage
    System.out.println("Routage");
    System.out.println();

    for (int i = 1; i < g.nbSommet(); i++) {
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

//Affichage Matrice
    System.out.println("matrice d'adjacence :\n");
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.print(g.matriceU()[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();

    bellmann B = new bellmann(0, g);
    B.plusCourtCheminBk();
    B.AfficheDist();
    B.AfficheRout();
  }
}
