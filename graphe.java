public class graphe {

  private int[][] U;
  private int NMAX = 100;
  private boolean[] valid;
  public static int ALPHA_NOTDEF = -1;

//il y a un probleme ......................
  public graphe() { // crée un graphe vide avec au max 1000 sommets
    NMAX = 1000;
    U = new int[NMAX][NMAX];
    valid = new boolean[NMAX];
    for (int i = 0; i < NMAX; i++) {
      valid[i] = false; // tous les sommets sont non valides
      for (int j = 0; j < NMAX; j++) {
        U[i][j] = ALPHA_NOTDEF; //toutes les valeurs sont à -1
      }
    }
  }

  public graphe(graphe g) {
    U = new int[NMAX][NMAX];
    valid = new boolean[NMAX];
    NMAX = g.NMAX;
    for (int i = 0; i < NMAX; i++) {
      valid[i] = g.valid[i];
      for (int j = 0; j < NMAX; j++) {
        U[i][j] = g.U[i][j];
      }
    }
  }

  public graphe(int nbs) {
    NMAX = nbs;
    U = new int[NMAX][NMAX];
    valid = new boolean[NMAX];
    for (int i = 0; i < NMAX; i++) {
      valid[i] = false;
      for (int j = 0; j < NMAX; j++) {
        U[i][j] = ALPHA_NOTDEF;
      }
    }
  }

  public graphe(int[][] mat) { //crÃ© un graphe a partir d'une matrice
    NMAX = 2 * mat.length; //d'adjacence
    U = new int[NMAX][NMAX];
    valid = new boolean[NMAX];
    for (int i = 0; i < NMAX; i++) {
      if (i < mat.length) {
        valid[i] = true;
      }
      else {
        valid[i] = false;

      }
      for (int j = 0; j < NMAX; j++) {
        if ( (i < mat.length) && (j < mat.length)) {
          U[i][j] = mat[i][j];
        }
        else {
          U[i][j] = ALPHA_NOTDEF;
        }
      }
    }
  }

  public boolean existesommet(int i) {
    return valid[i];
  }

  public int nbsommet() {
    int S = 0;
    for (int i = 0; i < NMAX; i++) {
      if (valid[i] == true) {
        S++;
      }
    }
    return S;
  }

  public boolean existearc(int i, int j) {
    return existesommet(i) && existesommet(j) && (U[i][j] != ALPHA_NOTDEF);
  }

  public int getvalarc(int i, int j) {
    if (existearc(i, j) != false) {
      return U[i][j];
    }
    else {
      return ALPHA_NOTDEF;
    }
  }

  public boolean ajoutsommet(int i) { //Si le sommet i n'existe pas rajoute le
    if (!valid[i]) { //sommet i
      valid[i] = true;
      return true;
    }
    else {
      return false;
    }
  }

  public boolean supprimesommet(int i) {
    if (valid[i] == true) {
      valid[i] = false;
      for (int j = 0; j < NMAX; j++) {
        U[i][j] = ALPHA_NOTDEF;
        U[j][i] = ALPHA_NOTDEF;
      }
      return true;
    }
    else {
      return false;
    }
  }

  public boolean ajoutarc(int i, int j, int val) {
    if (existesommet(i) && existesommet(j) && (U[i][j] == ALPHA_NOTDEF)) {
      U[i][j] = val;
      return true;
    }
    else {
      return false;
    }
  }

  public boolean supprimearc(int i, int j) {
    if (existesommet(i) && existesommet(j) && (U[i][j] != ALPHA_NOTDEF)) {
      U[i][j] = ALPHA_NOTDEF;
      return true;
    }
    else {
      return false;
    }
  }

  public int degreentrant(int i) {
    int D = 0;
    for (int j = 0; j < NMAX; j++) {
      if (U[j][i] != ALPHA_NOTDEF) {
        D++;
      }
    }
    return D;
  }

  public int degresortant(int i) {
    int D = 0;
    for (int j = 0; j < NMAX; j++) {
      if (U[i][j] != ALPHA_NOTDEF) {
        D++;
      }
    }
    return D;
  }

  public int degre(int i) {
    return degresortant(i) + degreentrant(i);
  }

  public int[] lst_succ(int i) {
    int[] lst = new int[degresortant(i)];
    int j = 0;
    int k = 0;
    while (k != degresortant(i)) {
      if (existearc(i, j)) {
        lst[k] = j;
        k++;
        j++;
      }
      else {
        j++;
      }
    }
    return lst;
  }

  public int[] lst_pred(int i) {
    int[] lst = new int[degreentrant(i)];
    int j = 0;
    int k = 0;
    while (k != degreentrant(i)) {
      if (existearc(j, i)) {
        lst[k] = j;
        k++;
        j++;
      }
      j++;
    }
    return lst;
  }

  public java.lang.String toString() {
    String g = ""; //des sommets suivi de leurs succeseurs
    int[] tab;
    for (int i = 0; i < NMAX; i++) {
      if (valid[i]) {
        g = g + i + " : ";
        tab = new int[degresortant(i)];
        tab = lst_succ(i);
        for (int j = 0; j < degresortant(i); j++) {
          g = g + tab[j] + " ";
        }
        g = g + "\n";

      }
    }
    return g;
  }

}
