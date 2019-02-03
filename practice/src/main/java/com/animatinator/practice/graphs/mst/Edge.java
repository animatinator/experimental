package com.animatinator.practice.graphs.mst;

import org.jetbrains.annotations.NotNull;

public class Edge implements Comparable<Edge> {
    final Node n1;
    final Node n2;
    private final int weight;

    Edge(Node n1, Node n2, int weight) {
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;
    }

    @Override
    public int compareTo(@NotNull Edge other) {
        return weight - other.weight;
    }
}
