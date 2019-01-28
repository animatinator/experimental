package com.animatinator.practice.graphs;

import java.util.ArrayList;
import java.util.List;

class GraphNode<T> {
    private final T value;
    private List<GraphNode<T>> neighbours;

    GraphNode(T value) {
        this.value = value;
        this.neighbours = new ArrayList<>();
    }

    List<GraphNode<T>> getNeighbours() {
        return neighbours;
    }

    private void linkTo(GraphNode<T> other) {
        neighbours.add(other);
    }

    static <T> void linkNodes(GraphNode<T> first, GraphNode<T> second) {
        first.linkTo(second);
        second.linkTo(first);
    }
}
