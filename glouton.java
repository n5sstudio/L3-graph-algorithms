/*
 Tournier Nicolas L3 - MI
 TP 4 : Coloration d'un Graphe.
 */

class glouton {
  private int[] couleur;
  private int[][] dSommet;
  private graphe g;
  private int nbCouleur;

  public glouton(int[][] mat) {
    g = new graphe(mat);
    dSommet = new int[g.nbsommet()][6];
    couleur = new int[g.nbsommet()];
    nbCouleur = 0;
    for (int i = 0; i < g.nbsommet(); i++) {
      dSommet[i][0] = i;
      dSommet[i][1] = g.degresortant(i);
    }
  }

  /* On trie les sommets par nombres de successeurs décroissant*/
  public void triDegreSommet() {
    for (int x = 0; x < g.nbsommet(); x++) {
      int i = 0;
      while ( (dSommet[i][2] >= dSommet[x][2]) && (i < x)) {
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
    }
    else {
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
    Pile[] sCouleur = new Pile[nbCouleur + 1];
    for (int i = 0; i < nbCouleur + 1; i++) {
      sCouleur[i] = new Pile(g.nbsommet());
      for (int j = 0; j < g.nbsommet(); j++) {
        if (couleur[j] == i) {
          sCouleur[i].empiler(j);
        }
      }
      System.out.println("Couleur " + (i + 1) + " - Sommets : ");
      System.out.println(sCouleur[i].toString());
    }
  }

  /* Programme Principal */
  public static void main(java.lang.String[] args) {

    int[][] mat = {
        /*0*/
        {
         -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1}
        ,
        /*1*/
        {
        1, -1, 1, 1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,
        /*2*/
        {
         -1, 1, -1, -1, 1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,
        /*3*/
        {
         -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,
        /*4*/
        {
         -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,
        /*5*/
        {
         -1, 1, 1, 1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,
        /*6*/
        {
         -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1}
        ,
        /*7*/
        {
         -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1}
        ,
        /*8*/
        {
         -1, -1, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,
        /*9*/
        {
         -1, -1, 1, -1, 1, 1, -1, -1, 1, -1, 1, -1, -1, 1, 1, 1, -1, -1, -1, -1,
        -1, -1}
        ,
        /*10*/
        {
         -1, -1, -1, 1, -1, 1, -1, -1, -1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1,
        -1, -1, -1}
        ,
        /*11*/
        {
         -1, -1, -1, 1, -1, -1, 1, -1, -1, -1, 1, -1, 1, -1, -1, -1, 1, -1, -1,
        -1, -1, -1}
        ,
        /*12*/
        {
         -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1}
        ,
        /*13*/
        {
         -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, 1,
        -1, -1, -1}
        ,
        /*14*/
        {
         -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, 1, -1, -1,
        -1, 1, -1, -1}
        ,
        /*15*/
        {
         -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, -1, -1,
        1, 1, -1}
        ,
        /*16*/
        {
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, -1,
        -1, -1, 1, 1}
        ,
        /*17*/
        {
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1}
        ,
        /*18*/
        {
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1,
        -1, 1, -1, -1}
        ,
        /*19*/
        {
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1,
        1, -1, 1, -1}
        ,
        /*20*/
        {
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1,
        -1, 1, -1, 1}
        ,
        /*21*/
        {
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1,
        -1, -1, 1, -1}
    };

    graphe map = new graphe(mat);
    System.out.println("Nb de Sommet : " + map.nbsommet());
    System.out.println(map.toString());
    glouton France = new glouton(mat);
    France.coloration();
    System.out.println("On a : " + France.getNbCouleur() + " couleurs differentes");
    System.out.println();
    System.out.println("Representation de la coloration : ");
    France.afficheCouleur();
    System.out.println();
  }
}
