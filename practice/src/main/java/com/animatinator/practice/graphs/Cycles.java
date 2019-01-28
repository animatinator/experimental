package com.animatinator.practice.graphs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Cycles {
    static <T> boolean hasCycle(GraphNode<T> root) {
        return cycleFromHere(root, root, new HashSet<>());
    }

    private static <T> boolean cycleFromHere(GraphNode<T> root, GraphNode<T> previous, Set<GraphNode<T>> seen) {
        if (seen.contains(root)) {
            return true;
        }
        seen.add(root);

        List<GraphNode<T>> neighbours = root.getNeighbours();

        for (GraphNode<T> node : neighbours) {
            if (node == previous) {
                continue;
            }

            if (cycleFromHere(node, root, seen)) {
                return true;
            }
        }

        return false;
    }
}
