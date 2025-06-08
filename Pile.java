public class Pile {
    private int[] P;
    private int sommet;
    private int tailleMax;

    public Pile() {
        tailleMax = 1000;
        sommet = -1;
        P = new int[tailleMax];
    }

    public Pile(int taille) {
        tailleMax = taille;
        sommet = -1;
        P = new int[tailleMax];
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
            System.exit(-1);
        }
        sommet++;
        P[sommet] = x;
    }

    public int depiler() {
        if (estVide()) {
            System.out.println("Pile::depiler(): Erreur la pile est vide");
            System.exit(-1);
        }
        int val = P[sommet];
        sommet--;
        return val;
    }

    public int sommetPile() {
        return P[sommet];
    }

    public String toString() {
        String result = "[";
        for (int i = 0; i < nbElement() - 1; i++) {
            result += " " + P[i] + " ,";
        }
        if (!estVide()) {
            result += " " + P[nbElement() - 1] + " ";
        }
        result += "]\n";
        return result;
    }

    // Fonction Appartient � ...
    public boolean appartient(Pile P, int z) {
        Pile A = P;
        boolean app = false;
        while (!app) {
            int k = A.depiler();
            if (k == z) {
                app = true;
            }
        }
        return app;
    }

    public static void main(String args[]) {
        Pile p = new Pile(3);
        System.out.println("Cr�ation de l'objet pile");
        System.out.println("la pile est-elle vide ? : " + p.estVide());
        System.out.println("p.empiler(1) :");
        p.empiler(1);
        System.out.println("etat de la pile :" + p);
        System.out.println("sommet de la pile :" + p.sommetPile());
        System.out.println("p.depiler(); valeur retourn�e :" + p.depiler());
        System.out.println("etat de la pile :" + p);
        System.out.println("p.empiler(1) :");
        p.empiler(1);
        System.out.println("p.empiler(2) :");
        p.empiler(2);
        System.out.println("p.empiler(3) :");
        p.empiler(3);
        System.out.println("etat de la pile :" + p);
        System.out.println("sommet de la pile :" + p.sommetPile());
        System.out.println();
        System.out.println("Test de l'erreur provoqu�e");
        System.out.println("p.depiler(); valeur retourn�e :" + p.depiler());
        System.out.println("p.depiler(); valeur retourn�e :" + p.depiler());
        System.out.println("p.depiler(); valeur retourn�e :" + p.depiler());
        System.out.println("p.depiler(); valeur retourn�e :" + p.depiler());
    }
} // Class Pile
