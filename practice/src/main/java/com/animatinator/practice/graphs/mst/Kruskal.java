package com.animatinator.practice.graphs.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Kruskal {
    static List<Edge> minimumSpanningTree(List<Node> nodes, List<Edge> edges) {
        Queue<Edge> edgeQueue = new PriorityQueue<>(edges);
        List<Edge> result = new ArrayList<>();

        DisjointSet<Node> nodeSet = new DisjointSet<>();
        for (Node n : nodes) {
            nodeSet.add(n);
        }

        while (!edgeQueue.isEmpty()) {
            Edge newEdge = edgeQueue.poll();
            assert(newEdge != null);

            if (!nodeSet.sameSet(newEdge.n1, newEdge.n2)) {
                result.add(newEdge);
                nodeSet.union(newEdge.n1, newEdge.n2);
            }
        }

        return result;
    }
}
