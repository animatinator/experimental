package com.animatinator.practice.graphs.topsort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TopologicalSort {
    private final Set<Node> outstandingNodes;
    private final List<Node> result = new ArrayList<>();

    TopologicalSort(List<Node> nodes) {
        outstandingNodes = new HashSet<>(nodes);
    }

    List<Node> topologicalSort() {
        while (!outstandingNodes.isEmpty()) {
            Node newNode = outstandingNodes.iterator().next();
            depthFirstThroughNode(newNode);
        }

        return result;
    }

    private void depthFirstThroughNode(Node node) {
        if (node.visited) {
            return;
        }

        node.visited = true;

        for (Node n : node.dependencies) {
            depthFirstThroughNode(n);
        }

        result.add(node);
        outstandingNodes.remove(node);
    }
}
