package com.animatinator.practice.graphs.topsort;

import java.util.ArrayList;
import java.util.List;

public class Node {
    final String name;
    final List<Node> dependencies = new ArrayList<>();
    boolean visited;

    public Node(String name) {
        this.name = name;
    }

    void addDependency(Node dependency) {
        dependencies.add(dependency);
    }
}
