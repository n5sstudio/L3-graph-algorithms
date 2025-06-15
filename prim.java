public class prim {

  private int[][] lstAreteTriee;
  private graphe g;
  private int poids;
  private int N;

  public prim(int[][] mat) {
    int t = 0;
    g = new graphe(mat);
    N = mat.length;
    poids = 0;
    lstAreteTriee = new int[N * N][2];
  }

  /* Trie les Arc en fonction de leur valeur */
  public void trieArete() {
    int tmp1;
    int tmp2;
    int k1 = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (g.existearc(i, j)) {
          lstAreteTriee[k1][0] = i;
          lstAreteTriee[k1][1] = j;
          k1++;
        }
      }
    }
    for (int i = 0; i < k1 - 1; i++) {
      for (int j = 0; j < k1 - i - 1; j++) {
        if (g.getvalarc(lstAreteTriee[j][0], lstAreteTriee[j][1]) >
            g.getvalarc(lstAreteTriee[j + 1][0], lstAreteTriee[j + 1][1])) {
          tmp1 = lstAreteTriee[j + 1][0];
          tmp2 = lstAreteTriee[j + 1][1];
          lstAreteTriee[j + 1][0] = lstAreteTriee[j][0];
          lstAreteTriee[j + 1][1] = lstAreteTriee[j][1];
          lstAreteTriee[j][0] = tmp1;
          lstAreteTriee[j][1] = tmp2;
        }
      }
    }
  }

  /* Choix de l'arête à valeur minimale */
  private int[] choixAreteMin(graphe A) {
    int i = -1;
    boolean test = true;
    int[] tab = new int[2];
    while (test == true) {
      i++;
      if ( (A.existesommet(lstAreteTriee[i][0]) == true) &&
          (A.existesommet(lstAreteTriee[i][1]) == false)) {
        test = false;
      }
      if ( (A.existesommet(lstAreteTriee[i][0]) == false) &&
          (A.existesommet(lstAreteTriee[i][1]) == true)) {
        test = false;
      }

    }
    tab[0] = lstAreteTriee[i][0];
    tab[1] = lstAreteTriee[i][1];
    return tab;
  }

  /* Alogrithme de Prim */
  private graphe fPrim() {
    int x;
    int y;
    int k = 0;
    graphe A = new graphe();
    trieArete();
    A.ajoutsommet(5);

    while (A.nbsommet() < N) {
      k++;
      x = choixAreteMin(A)[0];
      y = choixAreteMin(A)[1];
      if (A.existesommet(x)) {
        A.ajoutsommet(y);
      }
      else {
        A.ajoutsommet(x);
      }
      A.ajoutarc(x, y, g.getvalarc(x, y));
      poids = poids + g.getvalarc(x, y);
    }
    return A;
  }

  /* Retourne le poids de l'arbre */
  public int getPoids() {
    return poids;
  }

  /* Programme Principal */
  public static void main(String[] args) {

    int[][] mat = {
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

    graphe g = new graphe(mat);
    prim P = new prim(mat);
    System.out.println("Representation de l'arbre : ");
    System.out.println(P.fPrim());
    System.out.println();
    System.out.println("Poids de l'arbre : " + P.getPoids());
    System.out.println();
  }
}
