package com.animatinator.practice.graphs.dijkstra;

class Edge {
    final Node target;
    final int weight;

    Edge(Node target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}
