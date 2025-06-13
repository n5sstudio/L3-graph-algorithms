package com.n5sstudio;
/*****************************************************************************
 *****************************************************************************/

public class Graphe { //nom de la classe

  /***************************attributs de la classe****************************/

  private int NMAX = 1000;
  protected int[][] U;
  private boolean[] valid;
  private static int ALPHA_NOTDEF = 1000;

  /*************************constructeurs de la classe**************************/

  public Graphe() {
    NMAX = 1000; //initialisation de NMAX
    U = new int[NMAX][NMAX]; //declaration de la matrice U
    valid = new boolean[NMAX]; //declaration du tableau de booleen
    for (int i = 0; i < NMAX; i++) { //parcours
      for (int j = 0; j < NMAX; j++) { //de la matrice
        valid[i] = false; //initialisation du tableau
        U[i][j] = ALPHA_NOTDEF; //initialisation de la matrice
      }
    }
  } // fin graphe()

  public Graphe(int nbs) {
    NMAX = nbs; //initialisation du nombre de sommet
    U = new int[NMAX][NMAX];
    valid = new boolean[NMAX];
    for (int i = 0; i < NMAX; i++) {
      for (int j = 0; j < NMAX; j++) {
        valid[i] = false; //initialisation du tableau de booleen
        U[i][j] = ALPHA_NOTDEF; //initialisation de la matrice U
      }
    }
  } // fin graphe(int nbs)

  public Graphe(int[][] mat) {
    NMAX = 2 * mat.length;
    U = new int[NMAX][NMAX];
    valid = new boolean[NMAX];
    for (int i = 0; i < NMAX; i++) {
      if (i < mat.length) {
        valid[i] = true; //initialisation du tableau de booleen
      }
      else {
        valid[i] = false;
      }
      for (int j = 0; j < NMAX; j++) {
        if ( (i < mat.length) && (j < mat[0].length)) {
          U[i][j] = mat[i][j]; //initialisation de la matrice U
        }
        else {
          U[i][j] = ALPHA_NOTDEF; //si le sommet n_existe pas la valeur est -1
        }
      }
    }
  }

  public Graphe(Graphe g) { //constructeur de clonage
    NMAX = g.NMAX; //initialisation du nombre de sommet
    U = new int[NMAX][NMAX];
    valid = new boolean[NMAX];
    for (int i = 0; i < NMAX; i++) {
      for (int j = 0; j < NMAX; j++) {
        valid[i] = g.valid[i]; //initialisation du tableau de booleen
        U[i][j] = g.U[i][j]; //initialisation de la matrice U
      }
    }
  } // fin graphe(int nbs)

  /*****************************methodes de la classe***************************/

  public boolean existeSommet(int i) {
    return valid[i];
  } //retourne un booleen

  /*---------------------------------------------------------------------------*/
  public int nbSommet() { //fonction qui retourne un entier
    int a = 0;
    for (int i = 0; i < NMAX; i++) {
      if (valid[i] == true) { //si le sommet existe
        a++; //stocke le nombre de sommet
      }
    }
    return a; //retourne le nombre de sommets
  }

  /*---------------------------------------------------------------------------*/
  public boolean existeArc(int i, int j) { //fonction qui retourne un booleen
    if (U[i][j] != ALPHA_NOTDEF) {
      return true;
    }
    else {
      return false;
    }
  }

  /*---------------------------------------------------------------------------*/
  public int getValArc(int i, int j) { //fonction qui retourne un entier
    if (existeArc(i, j) == true) {
      return U[i][j]; //retourne la valeur de l_arc
    }
    else {
      return ALPHA_NOTDEF; //retourne -1 si l_arc n_existe pas
    }
  }

  /*---------------------------------------------------------------------------*/
  public boolean ajoutSommet(int i) {
    if (existeSommet(i) == true) {
      return false;
    }
    else {
      valid[i] = true;
      return true;
    } //ajoute le sommet et retourne true
  }

  /*---------------------------------------------------------------------------*/
  public boolean supprimeSommet(int i) {
    if (existeSommet(i) == true) {
      valid[i] = false;
      return true;
    } //supprime le sommet et retourne true
    else {
      return false;
    }
  }

  /*---------------------------------------------------------------------------*/
  public boolean ajoutArc(int i, int j, int val) {
    if (existeArc(i, j) == true) {
      return false;
    } //retourne faux

    if ( (existeSommet(i) && existeSommet(j))) { // ne pas ajouter d'arc si un
      U[i][j] = val;
      return true;
    } // sommet n_existe pas
    return false;

  }

  /*---------------------------------------------------------------------------*/
  public boolean supprimeArc(int i, int j) {
    if (existeArc(i, j) == false) { //si l_arc n_existe pas
      return false;
    } //arc non ajoute
    else {
      U[i][j] = ALPHA_NOTDEF; //arc supprime
      return true;
    } //retourne vrai
  }

  /*---------------------------------------------------------------------------*/
  public int degreEntrant(int i) { //fonction qui retourne un entier
    int X = 0; //on fixe la valeur 0 � un entier X
    if (existeSommet(i) == true) { //si sommet existe
      for (int j = 0; j < NMAX; j++) {
        if (existeArc(j, i) == true) { //si arc(j,i) existe
          X = X + 1; //stocke le nombre d_arc entrant
        }
      } // fin du for
      return X;
    } //retourne le degre entrant
    else {
      return ALPHA_NOTDEF;
    } //si sommet n_existe pas retourne -1
  }

  /*---------------------------------------------------------------------------*/
  public int degreSortant(int i) {
    int X = 0; //on fixe la valeur 0 � un entier X
    if (existeSommet(i) == true) { //si sommet existe
      for (int j = 0; j < NMAX; j++) {
        if (existeArc(i, j) == true) { //si arc(i,j) existe
          X = X + 1; //stocke le nombre d_arc entrant
        }
      } // fin du for
      return X;
    } //retourne le degre sortant
    else {
      return ALPHA_NOTDEF;
    } //si sommet n_existe pas retourne -1
  }

  /*---------------------------------------------------------------------------*/
  public int degre(int i) {
    if (existeSommet(i) == true) {
      return (degreSortant(i) + degreEntrant(i)); //addition des degres I/O
    }
    else {
      return ALPHA_NOTDEF;
    }

  }

  /*---------------------------------------------------------------------------*/
  public int[] lst_succ(int i) { //fonction qui retourne un tableau
    int k = 0;
    int[] tab = new int[0];
    if (existeSommet(i) == true) {
      tab = new int[degreSortant(i)]; //tableau bien dimensionne

      for (int j = 0; j < NMAX; j++) {
        if (existeArc(i, j) == true) {
          tab[k] = j;
          k = k + 1;
        } //k sert d'indicage au tableau

      }

    }
    return tab; //retourne le tableau
  }

  /*---------------------------------------------------------------------------*/
  public int[] lst_pred(int i) {
    int k = 0;
    int[] tab = new int[0]; //declaration variable
    if (existeSommet(i) == true) {
      tab = new int[degreEntrant(i)]; //tableau bien dimensionne

      for (int j = 0; j < NMAX; j++) {
        if (existeArc(j, i) == true) {
          tab[k] = j;
          k = k + 1;
        } //k sert d'indicage au tableau
      }

    }
    return tab; //retourne le tableau
  }

  /*---------------------------------------------------------------------------*/
  public boolean estReflexif() { // retourne un booleen
    int X = 0;
    for (int i = 0; i < nbSommet(); i++) {
      if (existeArc(i, i) == true) { //test d'une boucle sur les sommets
        X = X + 1;
      }
    } //variable qui d�termine le nbre de boucle(s)
    if (X == nbSommet()) { //teste si le nbre de boucle = le nombre de sommet
      return true;
    }
    else {
      return false;
    }
  }

  /*---------------------------------------------------------------------------*/
  public boolean estAntiReflexif() {
    int X = 0;
    for (int i = 0; i < nbSommet(); i++) {
      if (existeArc(i, i) == true) { //test d'une boucle sur les sommets
        X = X + 1;
      }
    } //variable qui d�termine le nbre de boucle(s)
    if (X == 0) { //teste si le nbre de boucle(s) est bien nul
      return true;
    }
    else {
      return false;
    }
  }

  /*---------------------------------------------------------------------------*/
  public boolean estSymetrique() {
    int X = 0;
    int Y = 0;
    for (int i = 0; i < nbSommet(); i++) { //parcours de
      for (int j = 0; j < nbSommet(); j++) { //deux indices
        if ( (existeArc(i, j) && existeArc(j, i) == true) && (i != j)) {
          X++; //stocke le nombre de sym�trie
        }
        if ( (existeArc(i, j) && (i != j))) {
          Y++;
        }
      }
    }
    if (X == Y) { //test logique
      return true;
    }
    else {
      return false;
    }
  }

  /*---------------------------------------------------------------------------*/
  public boolean estAntiSymetrique() {
    int X = 0;
    for (int i = 0; i < nbSommet(); i++) { //parcours de
      for (int j = 0; j < nbSommet(); j++) { //deux indices
        if ( (existeArc(i, j) && existeArc(j, i) == true) && (i != j)) {
          X++; //stocke le nombre de sym�trie
        }
      }
    }
    if (X == 0) { //le nombre de sym�trie doit etre nul
      return true;
    }
    else {
      return false;
    }
  }

  /*---------------------------------------------------------------------------*/
  public boolean estTransitif() { //fonction qui retourne un booleen
    int X = 0;
    int Y = 0;
    for (int m = 0; m < nbSommet(); m++) { //calcule le nombre d_arc du graphe
      for (int n = 0; n < nbSommet(); n++) {
        if (U[m][n] != ALPHA_NOTDEF) {
          Y++; //nombre d_arc du graphe
        }
      }
    }
    for (int i = 0; i < nbSommet(); i++) { //parcours
      for (int j = 0; j < nbSommet(); j++) { //de trois
        for (int k = 0; k < nbSommet(); k++) { //indices
          if (existeArc(j, k) && existeArc(k, i) && existeArc(j, i)) {
            X++; //stocke les arcs qui r�pondent � la transitivite
          }
        } //fin for 3
      } //fin for 2
    } //fin for 1
    if (X == nbSommet() * Y) { //test logique
      return true;
    }
    else {
      return false;
    }
  }

  /*---------------------------------------------------------------------------*/
  public boolean estAntiTransitif() { //fonction qui retourne un booleen
    int X = 0;
    for (int i = 0; i < nbSommet(); i++) { //parcours
      for (int j = 0; j < nbSommet(); j++) { //de trois
        for (int k = 0; k < nbSommet(); k++) { //indices
          if (existeArc(j, k) && existeArc(k, i) && existeArc(j, i)) {
            X++; //stocke les arcs qui r�pondent � la transitivite
          }
        } //fin for 3
      } //fin for 2
    } //fin for 1
    if (X == 0) { //test logique
      return true;
    }
    else {
      return false;
    }
  }

  /*---------------------------------------------------------------------------*/
  public int[][] transposition() { //fonction qui retourne une matrice
    int[][] V = new int[nbSommet()][nbSommet()];
    for (int i = 0; i < nbSommet(); i++) { //parcours
      for (int j = 0; j < nbSommet(); j++) { //de deux indices
        V[i][j] = U[j][i];
      } //transposition
    }
    return V; //retourne la matrice U transposee
  }

  /*---------------------------------------------------------------------------*/
  public int[][] matriceU() { //fonction qui retourne une matrice
    return U;
  } //retourne la matrice U

  /*---------------------------------------------------------------------------*/
  public void sousgraphe(int[] lst_sommet) { //procedure
    for (int i = 0; i < lst_sommet.length; i++) {
      supprimeSommet(lst_sommet[i]);
    } //suppression du sommet
    for (int k = 0; k < lst_sommet.length; k++) {
      for (int j = 0; j < nbSommet(); j++) {
        if (existeArc(lst_sommet[k], j)) {
          supprimeArc(lst_sommet[k], j); //suppression des arcs
        }
        if (existeArc(j, lst_sommet[k])) {
          supprimeArc(j, lst_sommet[k]); //suppression des arcs
        }
      }
    }
  }

  /*---------------------------------------------------------------------------*/
  public java.lang.String toString() { //fonction qui retourne un string
    int[] tab; //declaration tableau
    String ch;
    ch = ""; //initialisation
    for (int i = 0; i < NMAX; i++) {
      if (existeSommet(i)) {
        tab = lst_succ(i); //stocke les successeurs de i
        ch = ch + "sommet " + i + " : ";
        for (int j = 0; j < degreSortant(i); j++) {
          ch = ch + tab[j] + " ";
        }
        ch = ch + "\n";
      }
    }
    return ch;
  }

  /*---------------------------------------------------------------------------*/
  public Graphe union(Graphe g) {
    Graphe G4 = new Graphe(g); //cr�e un clone du graphe en parametre
    Graphe nul = new Graphe(); //cr�e un graphe vide
    if (nbSommet() == g.nbSommet()) { //le nombre de sommet doit etre le meme
      for (int i = 0; i < nbSommet(); i++) {
        for (int j = 0; j < nbSommet(); j++) {
          if (U[i][j] == ALPHA_NOTDEF) {
            if (g.U[i][j] == ALPHA_NOTDEF) {
              G4.U[i][j] = ALPHA_NOTDEF;
            }
          }
          else {
            if (g.U[i][j] == ALPHA_NOTDEF) {
              G4.U[i][j] = U[i][j];
            }
            else {
              if (g.U[i][j] != U[i][j]) { //si les valeur sont differentes
                System.out.println("****ERREUR****");
                System.exit(0);
              }
            }
          }
        }
      }
      return G4;
    }
    else {
      return nul;
    }
  }

  /*---------------------------------------------------------------------------*/
  public Graphe composition(Graphe g) {
    Graphe G5 = new Graphe(); //cr�e un graphe vide
    for (int i = 0; i < nbSommet(); i++) { //parcours
      for (int j = 0; j < degreSortant(i); j++) { //de
        for (int k = 0; k < g.nbSommet(); k++) { //quatre
          for (int m = 0; m < g.degreEntrant(k); m++) { //indices
            if ( (lst_succ(i)[j] == g.lst_pred(k)[m])) {
              G5.ajoutSommet(i); //ajout des sommets
              G5.ajoutSommet(k);
              G5.U[i][k] = 1;
            } //cr�e l_arc compose des deux graphes
          }
        }

      }
    }
    return G5;
  }

  /*---------------------------------------------------------------------------*/

  public void modifArc(int i, int j, int val) {
    if (existeArc(i, j) == true) {
      U[i][j] = val;
    }
  }
}

  /*****************************fonction principale*****************************/
