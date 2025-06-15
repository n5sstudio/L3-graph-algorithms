public class Pile {
  private int[] P;
  private int sommet;
  private int tailleMax = 36;

  public Pile() {
    tailleMax = 36;
    sommet = -1;
    P = new int[tailleMax];
  }

  public Pile(int taille) {
    tailleMax = taille;
    sommet = -1;
    P = new int[tailleMax];
  }

  public Pile(Pile p) {
    P = p.P;
    sommet = p.sommet;
    tailleMax = p.tailleMax;
  }

  public Pile(int x, graphe g) {
    sommet = -1;
    P = new int[tailleMax];
    int[] tab = g.lst_succ(x);
    for (int i = 0; i < g.degresortant(x); i++) {
      if (tab[i] != x) {
        empiler(tab[i]);

      }
    }
  }

  public boolean estVide() {
    return (sommet < 0);
  }

  public boolean estPleine() {
    return (sommet >= tailleMax);
  }

  public int nbElement() {
    return (sommet + 1);
  }

  public void empiler(int x) {
    if (estPleine()) {
      System.out.println("Pile::empiler():Erreur la pile est pleine");
      System.exit( -1);
    }
    sommet++;
    P[sommet] = x;
  }

  public int depiler() {
    if (estVide()) {
      System.out.println("Pile::depiler():Erreur la pile est vide");
      System.exit( -1);
    }
    int val = P[sommet];
    sommet--;
    return val;
  }

  public int sommetPile() {
    return P[sommet];
  }

  public boolean appartient(int x) {
    int i = 0;
    while (i < nbElement()) {
      if (P[i] == x) {
        return true;
      }
      i++;
    }
    return false;
  }

  public String toString() {
    String result = "[";
    for (int i = 0; i < nbElement() - 1; i++) {
      result += " " + P[i] + " , ";
    }
    if (!estVide()) {
      result += " " + P[nbElement() - 1] + " ";
    }
    result += "]\n";
    return result;
  }
}
