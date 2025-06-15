package com.n5sstudio;

import com.n5sstudio.exceptions.ArcAlreadyExistsException;
import com.n5sstudio.exceptions.VertexAlreadyExistsException;
import com.n5sstudio.exceptions.VertexDoesNotExistsException;
import com.n5sstudio.exceptions.VertexOutboundLimitException;

public class Kruskal {

    private Graph tree;
    private Arc[] lstAreteTriee;
    private Graph graph;
    private int poids;
    private Stack[] stacks;

    public Kruskal(int[][] mat) throws VertexAlreadyExistsException, VertexOutboundLimitException {
        graph = new Graph(mat);
        for (int i = 0; i < 11; i++) {
            if (graph.hasVertex(i)) {
                tree.addVertex(i);
            }
        }
        poids = 0;
        lstAreteTriee = new Arc[55];
        stacks = new Stack[graph.getMaximumNumberOfVertex()];
    }

    private Arc[] listeArc() throws VertexOutboundLimitException {
        Arc[] l = new Arc[55];
        int k = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <= i; j++) {
                if (graph.hasArc(i, j)) {
                    l[k] = new Arc(i, j, graph.getArcValue(i, j));
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

    private Arc aretesuivante(int i) {
        return lstAreteTriee[i];
    }

    public void fKruskal()
            throws VertexOutboundLimitException, ArcAlreadyExistsException, VertexDoesNotExistsException {
        int indice = 0;
        lstAreteTriee = trielstArete();
        int k = 0;
        for (int x = 0; x < 11; x++) {
            stacks[x] = new Stack(graph.getMaximumNumberOfVertex());
            stacks[x].push(x);
        }
        Arc suiv = aretesuivante(indice);
        while (k < graph.getMaximumNumberOfVertex() - 1) {
            tree.addArc(suiv.getOrigin(), suiv.getDestination(), suiv.getValue());
            for (int i = 0; i < graph.getMaximumNumberOfVertex() + 1; i++) {
                if (stacks[suiv.getOrigin()].getSize() < graph.getMaximumNumberOfVertex() + 1
                        && stacks[suiv.getDestination()].contains(stacks[suiv.getDestination()], i)) {
                    stacks[suiv.getOrigin()].push(i);
                }
            }
            for (int z = 0; z < 11; z++) {
                if (stacks[suiv.getOrigin()].contains(stacks[suiv.getOrigin()], z)) {
                    for (int w = 0; w < graph.getMaximumNumberOfVertex() + 1; w++) {
                        if (stacks[suiv.getOrigin()].contains(stacks[suiv.getOrigin()], w)) {
                            stacks[suiv.getOrigin()] = new Stack(15);
                            stacks[z].push(w);
                        }
                    }
                }
            }
            k = k + 1;
            indice++;
            suiv = aretesuivante(indice);
        }
    }

    public int getPoids() throws VertexOutboundLimitException {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (tree.hasArc(i, j)) {
                    poids = poids + graph.getArcValue(i, j);
                }
            }
        }
        return poids;
    }

}
