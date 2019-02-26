package com.animatinator.practice.graphs.bellmanford;

import java.util.Set;

class Graph {
    final Set<Vertex> vertices;
    final Set<Edge> edges;

    Graph(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }
}
