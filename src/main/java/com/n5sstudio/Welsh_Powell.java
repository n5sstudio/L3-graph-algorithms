/*
 Tournier Nicolas L3 - MI
 TP 4 : Coloration d'un Graphe.
 */

public class Welsh_Powell {
  private int[] color;
  private int[][] dSommet;
  private graphe g;
  private int nbColor;

  public Welsh_Powell(int[][] mat) {
    g = new graphe(mat);
    dSommet = new int[g.nbsommet()][2];
    color = new int[g.nbsommet()];
    nbColor = 0;
    for (int i = 0; i < g.nbsommet(); i++) {
      dSommet[i][0] = i;
      dSommet[i][1] = g.degresortant(i);
    }
  }

  /* On trie les sommets par nombres de successeurs décroissant*/
  public void triDegreSommet() {
    for (int x = 1; x < g.nbsommet(); x++) {
      int k = 0;
      while ( (dSommet[k][1] >= dSommet[x][1]) && (k < x)) {
        k++;
      }
      int[] tmp = new int[2];
      tmp[0] = dSommet[x][0];
      tmp[1] = dSommet[x][1];
      for (int y = x - 1; y >= k; y--) {
        dSommet[y + 1][0] = dSommet[y][0];
        dSommet[y + 1][1] = dSommet[y][1];
      }
      dSommet[k][0] = tmp[0];
      dSommet[k][1] = tmp[1];
    }
  }

  /* On vérifie si l'on peut mettre cette couleur au sommet s */
  public boolean possibleCouleur(int s, int c) {
    boolean ok = true;
    int i = 0;
    while ( (ok) && (i < g.degresortant(s))) {
      ok = ( (color[g.lst_succ(s)[i]]) != c);
      i++;
    }
    return ok;
  }

  /* Coloration */
  public void coloration() {
    triDegreSommet();
    int couvert = 0;
    for (int i = 0; i < g.nbsommet(); i++) {
      color[i] = nbColor;
    }
    while (couvert < g.nbsommet()) {
      nbColor++;
      for (int j = 0; j < g.nbsommet(); j++) {
        int s = dSommet[j][0];
        if ( (color[s] == 0) && (possibleCouleur(s, nbColor))) {
          color[s] = nbColor;
          couvert++;
        }
      }
    }
  }

  /* On retourne le nombre de couleurs */
  public int getNbColor() {
    return nbColor;
  }

  /* Foction d'affichage */
  public void afficheColor() {
    Pile[] sommetColore = new Pile[nbColor + 1];
    for (int i = 1; i <= nbColor; i++) {
      sommetColore[i] = new Pile(g.nbsommet());
      for (int j = 0; j < g.nbsommet(); j++) {
        if (color[j] == i) {
          sommetColore[i].empiler(j);
        }
      }
      System.out.println("Couleur " + i + " - Sommets : ");
      System.out.println(sommetColore[i].toString());
    }
  }


  /* Programme Principal */
  public static void main(java.lang.String[] args) {

    int[][] mat = {
        {
        -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1}
        ,

        {
        1, -1, 1, 1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,

        {
        -1, 1, -1, -1, 1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,

        {
        -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,

        {
        -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,

        {
        -1, 1, 1, 1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,

        {
        -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1}
        ,

        {
        -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1}
        ,

        {
        -1, -1, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1,
        -1, -1, -1}
        ,

        {
        -1, -1, 1, -1, 1, 1, -1, -1, 1, -1, 1, -1, -1, 1, 1, 1, -1, -1, -1, -1,
        -1, -1}
        ,

        {
        -1, -1, -1, 1, -1, 1, -1, -1, -1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1,
        -1, -1, -1}
        ,

        {
        -1, -1, -1, 1, -1, -1, 1, -1, -1, -1, 1, -1, 1, -1, -1, -1, 1, -1, -1,
        -1, -1, -1}
        ,

        {
        -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1}
        ,

        {
        -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, 1,
        -1, -1, -1}
        ,

        {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, 1, -1, -1,
        -1, 1, -1, -1}
        ,

        {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, -1, -1,
        1, 1, -1}
        ,

        {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, -1,
        -1, -1, 1, 1}
        ,

        {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1}
        ,

        {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1,
        -1, 1, -1, -1}
        ,

        {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1,
        1, -1, 1, -1}
        ,

        {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1,
        -1, 1, -1, 1}
        ,

        {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1,
        -1, -1, 1, -1}
    };

    graphe mapFr = new graphe(mat);
    System.out.println("Nb de Sommet : " + mapFr.nbsommet());
    System.out.println();
    System.out.println("Representation du Graphe : ");
    System.out.println(mapFr.toString());

    // Exécution
    Welsh_Powell france = new Welsh_Powell(mat);
    france.coloration();
    System.out.println("On a : " + france.getNbColor() +
                       " couleurs differentes");
    System.out.println();
    System.out.println("Representation de la coloration : ");
    france.afficheColor();
    System.out.println();
  }

  /*Correspondance Régions - Sommets.
    Nord -> 0
    Picardie -> 1
    Haute normandie -> 2
    Champagne -> 3
    Basse normandie -> 4
    Ile de france -> 5
    Lorraine -> 6
    Bretagne -> 7
    Pays de la loire -> 8
    Centre -> 9
    Bourgogne -> 10
    Franche comté -> 11
    Alsace -> 12
    Poitou charente -> 13
    Limousin -> 14
    Auvergne -> 15
    Rhone alpes -> 16
    Corse -> 17
    Aquitaineé -> 18
    Midi pyrennee -> 19
    Languedoc roussillon -> 20
    P.A.C.A -> 21*/

}
