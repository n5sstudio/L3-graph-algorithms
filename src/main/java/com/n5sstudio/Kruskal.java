package com.n5sstudio;

public class Kruskal {

    private static graphe A;
    private arc[] lstAreteTriee;
    private graphe grapheG;
    private int poids;
    public Pile[] C;

    public Kruskal(int[][] mat) {
        grapheG = new graphe(mat);
        A = new graphe();
        for (int i = 0; i < 11; i++) {
            if (grapheG.existesommet(i)) {
                A.ajoutsommet(i);
            }
        }
        poids = 0;
        lstAreteTriee = new arc[55];
        C = new Pile[grapheG.nbsommet()];
    }

    /* Cr�er la liste des Arc */
    private arc[] listeArc() {
        arc[] l = new arc[55];
        int k = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <= i; j++) {
                if (grapheG.existearc(i, j)) {
                    l[k] = new arc(i, j, grapheG.getvalarc(i, j));
                    k++;
                }
            }
        }
        return l;
    }

    /* Trie la liste d'Arc */
    private arc[] trielstArete() {
        arc[] l = listeArc();
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l.length - i - 1; j++) {
                if (l[j].getval() > l[j + 1].getval()) {
                    arc tmp = l[j];
                    l[j] = l[j + 1];
                    l[j + 1] = tmp;
                }
            }
        }
        return l;
    }

    /* Returne l'arc d'indice i */
    private arc aretesuivante(int i) {
        return lstAreteTriee[i];
    }

    /* Algo de Kruskal */
  public void fKruskal() {
    int indice = 0;
    lstAreteTriee = trielstArete();
    int k = 0;
    for (int x = 0; x < 11; x++) {
      C[x] = new Pile();
      C[x].empiler(x);

    }
    arc suiv = aretesuivante(indice);
    while ( (k < grapheG.nbsommet() - 1) && getOriginVertexId()))) {
        A.ajoutarc(suiv.getX(), suiv.getOriginVertexId(), suiv.getval());
        for (int i = 0; i < grapheG.nbsommet() + 1; i++) {
          if (C[suiv.getOriginVertexId < grapheG.nbsommet() + 1; i++) {
          if (C[suiv.getY()].appartient(i)) {
            C[suiv.getX()].empiler(i);
          }
        }
        for (int z = 0; z < 11; z++) {
          if (C[suiv.getX()].appartient(z)) {
            for (int i = 0; i < grapheG.nbsommet() + 1; i++) {
              if (C[suiv.getX()].appartient(i)) {
                C[suiv.getX()] = new Pile(15);
                C[z].empiler(i);
              }
            }
          }
        }
        k = k + 1;
      }
      indice++;
      suiv = aretesuivante(indice);
    }
  }

    /* Affichage de l'Arbre Couvrant */
    public void getArbre() {
        for (int i = 0; i < 11; i++) {
            if (A.existesommet(i)) {
                System.out.print(i + ": ");
                for (int j = 0; j < A.degresortant(i); j++) {
                    System.out.print(A.lst_succ(i)[j] + " ");
                }
                System.out.println(" ");
            }
        }
    }

    /* Retourne le Poids de l'Arbre */
    public int getPoids() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (A.existearc(i, j)) {
                    poids = poids + grapheG.getvalarc(i, j);
                }
            }
        }
        return poids;
    }

}
