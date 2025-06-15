package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Kruskal {

    private static Graph A;
    private Arc[] lstAreteTriee;
    private Graph GraphG;
    private int poids;
    public Stack[] C;

    public Kruskal(int[][] mat) throws VertexAlreadyExistsException, VertexOutboundLimitException {
        GraphG = new Graph(mat);
        A = new Graph();
        for (int i = 0; i < 11; i++) {
            if (GraphG.hasVertex(i)) {
                A.addVertex(i);
            }
        }
        poids = 0;
        lstAreteTriee = new Arc[55];
        C = new Stack[GraphG.getMaximumNumberOfVertex()];
    }

    private Arc[] listeArc() throws VertexOutboundLimitException {
        Arc[] l = new Arc[55];
        int k = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <= i; j++) {
                if (GraphG.hasArc(i, j)) {
                    l[k] = new Arc(i, j, GraphG.getArcValue(i, j));
                    k++;
                }
            }
        }
        return l;
    }

    private Arc[] trielstArete() throws VertexOutboundLimitException {
        Arc[] l = listeArc();
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l.length - i - 1; j++) {
                if (l[j].getValue() > l[j + 1].getValue()) {
                    Arc tmp = l[j];
                    l[j] = l[j + 1];
                    l[j + 1] = tmp;
                }
            }
        }
        return l;
    }

    /* Returne l'arc d'indice i */
    private Arc aretesuivante(int i) {
        return lstAreteTriee[i];
    }

    /* Algo de Kruskal */
  public void fKruskal() throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
    int indice = 0;
    lstAreteTriee = trielstArete();
    int k = 0;
    for (int x = 0; x < 11; x++) {
      C[x] = new Stack(GraphG.getMaximumNumberOfVertex());
      C[x].push(x);
    }
    Arc suiv = aretesuivante(indice);
    while (k < GraphG.getMaximumNumberOfVertex() - 1) {
        A.addArc(suiv.getOrigin(), suiv.getDestination(), suiv.getValue());
        for (int i = 0; i < GraphG.getMaximumNumberOfVertex() + 1; i++) {
          if (C[suiv.getOrigin()].getSize() < GraphG.getMaximumNumberOfVertex() + 1) {
            if (C[suiv.getDestination()].contains(C[suiv.getDestination()], i)) {
                C[suiv.getOrigin()].push(i);
            }
        }
        for (int z = 0; z < 11; z++) {
          if (C[suiv.getOrigin()].contains(C[suiv.getOrigin()], z)) {
            for (int w = 0; w < GraphG.getMaximumNumberOfVertex() + 1; w++) {
              if (C[suiv.getOrigin()].contains(C[suiv.getOrigin()], w)) {
                C[suiv.getOrigin()] = new Stack(15);
                C[z].push(w);
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

    public void getArbre() throws VertexOutboundLimitException {
        for (int i = 0; i < 11; i++) {
            if (A.hasVertex(i)) {
                System.out.print(i + ": ");
                for (int j = 0; j < A.getVertexOutDegree(i); j++) {
                    System.out.print(A.getSuccessorList(i)[j] + " ");
                }
                System.out.println(" ");
            }
        }
    }

    public int getPoids() throws VertexOutboundLimitException {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (A.hasArc(i, j)) {
                    poids = poids + GraphG.getArcValue(i, j);
                }
            }
        }
        return poids;
    }

}
