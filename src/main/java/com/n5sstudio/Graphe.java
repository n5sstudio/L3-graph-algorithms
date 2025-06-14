/*package com.n5sstudio;

public class Graphe {

    /* ---------------------------------------------------------------------------
    public Graphe union(Graphe g) {
        Graphe G4 = new Graphe(g); // cr�e un clone du graphe en parametre
        Graphe nul = new Graphe(); // cr�e un graphe vide
        if (nbSommet() == g.nbSommet()) { // le nombre de sommet doit etre le meme
            for (int i = 0; i < nbSommet(); i++) {
                for (int j = 0; j < nbSommet(); j++) {
                    if (U[i][j] == ALPHA_NOTDEF) {
                        if (g.U[i][j] == ALPHA_NOTDEF) {
                            G4.U[i][j] = ALPHA_NOTDEF;
                        }
                    } else {
                        if (g.U[i][j] == ALPHA_NOTDEF) {
                            G4.U[i][j] = U[i][j];
                        } else {
                            if (g.U[i][j] != U[i][j]) { // si les valeur sont differentes
                                System.out.println("****ERREUR****");
                                System.exit(0);
                            }
                        }
                    }
                }
            }
            return G4;
        } else {
            return nul;
        }
    }

    /* ---------------------------------------------------------------------------
    public Graphe composition(Graphe g) {
        Graphe G5 = new Graphe(); // cr�e un graphe vide
        for (int i = 0; i < nbSommet(); i++) { // parcours
            for (int j = 0; j < degreSortant(i); j++) { // de
                for (int k = 0; k < g.nbSommet(); k++) { // quatre
                    for (int m = 0; m < g.degreEntrant(k); m++) { // indices
                        if ((lst_succ(i)[j] == g.lst_pred(k)[m])) {
                            G5.ajoutSommet(i); // ajout des sommets
                            G5.ajoutSommet(k);
                            G5.U[i][k] = 1;
                        } // cr�e l_arc compose des deux graphes
                    }
                }

            }
        }
        return G5;
    }

    /*---------------------------------------------------------------------------
    public void sousgraphe(int[] lst_sommet) { // procedure
        for (int i = 0; i < lst_sommet.length; i++) {
            supprimeSommet(lst_sommet[i]);
        } // suppression du sommet
        for (int k = 0; k < lst_sommet.length; k++) {
            for (int j = 0; j < nbSommet(); j++) {
                if (existeArc(lst_sommet[k], j)) {
                    supprimeArc(lst_sommet[k], j); // suppression des arcs
                }
                if (existeArc(j, lst_sommet[k])) {
                    supprimeArc(j, lst_sommet[k]); // suppression des arcs
                }
            }
        }
    }
        */
    
}
