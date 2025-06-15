/*
 Tournier Nicolas L3 - MI
 TP 4 : Calcul de l'arbre de poids minimum.
 */


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

  /* Créer la liste des Arc */
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
    while ( (k < grapheG.nbsommet() - 1) && (indice != 55)) {
      if (! (C[suiv.getX()].appartient(suiv.getY()))) {
        A.ajoutarc(suiv.getX(), suiv.getY(), suiv.getval());
        for (int i = 0; i < grapheG.nbsommet() + 1; i++) {
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


  /* Programme Principal */
  public static void main(String[] args) {
    int[][] matcout = {
        {
         -1, 40, 19, 20, 85, 28, 70, 45, 50, 62, 85}
        , {
        40, -1, 55, 21, 90, 35, 90, 125, 23, 90, 55}
        , {
        19, 55, -1, 35, 55, 25, 45, 25, 50, 45, 75}
        , {
        20, 21, 35, -1, 70, 15, 75, 80, 22, 92, 65}
        , {
        85, 90, 55, 70, -1, 54, 18, 35, 75, 26, 26}
        , {
        28, 35, 25, 15, 54, -1, 60, 32, 21, 49, 52}
        , {
        70, 90, 45, 75, 18, 60, -1, 24, 80, 16, 27}
        , {
        45, 125, 25, 80, 35, 32, 24, -1, 65, 17, 35}
        , {
        50, 23, 50, 22, 75, 21, 80, 65, -1, 72, 51}
        , {
        62, 90, 45, 92, 26, 49, 16, 17, 72, -1, 35}
        , {
        85, 55, 75, 65, 26, 52, 27, 35, 51, 35, -1}
    };

    graphe g = new graphe(matcout);
    Kruskal K = new Kruskal(matcout);
    K.fKruskal();
    System.out.println("Representation de l'arbre : ");
    K.getArbre();
    System.out.println();
    System.out.println("Poids de l'Arbre : " + K.getPoids());
  }

};
