package com.animatinator.practice.graphs.dijkstra;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
    private static int NEXT_ID = 0;

    private final int id;

    List<Edge> edges;

    int dist = Integer.MAX_VALUE;
    Node predecessor;

    Node() {
        this.id = NEXT_ID;
        edges = new ArrayList<>();
        predecessor = null;
    }

    private void addEdge(Node other, int weight) {
        edges.add(new Edge(other, weight));
    }

    static void joinNodes(Node n1, Node n2, int weight) {
        n1.addEdge(n2, weight);
        n2.addEdge(n1, weight);
    }

    @Override
    public int compareTo(@NotNull Node other) {
        return dist - other.dist;
    }
}
