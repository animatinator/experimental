package com.animatinator.practice.graphs.bellmanford;

import java.util.Set;

class BellmanFord {
    static void runBellmanFord(Graph graph, Vertex source) throws CycleException {
        assert(graph.vertices.contains(source));
        Set<Edge> edges = graph.edges;
        Set<Vertex> vertices = graph.vertices;

        initialiseDistances(vertices, source);

        for (int i = 0; i < vertices.size(); i++) {
            for (Edge edge : edges) {
                relaxEdge(edge);
            }
        }

        verifyNoNegativeCycles(edges);
    }

    private static void relaxEdge(Edge edge) {
        float relaxedDist = edge.v1.dist + edge.weight;

        if (relaxedDist < edge.v2.dist) {
            edge.v2.dist = relaxedDist;
            edge.v2.predecessor = edge.v1;
        }
    }

    private static void initialiseDistances(Set<Vertex> vertices, Vertex source) {
        for (Vertex v : vertices) {
            if (v == source) {
                v.dist = 0;
            } else {
                v.dist = Integer.MAX_VALUE;
            }
        }
    }

    private static void verifyNoNegativeCycles(Set<Edge> edges) throws CycleException {
        for (Edge edge : edges) {
            if (edge.v1.dist + edge.weight < edge.v2.dist) {
                throw new CycleException();
            }
        }
    }

    static class CycleException extends Exception {}
}
