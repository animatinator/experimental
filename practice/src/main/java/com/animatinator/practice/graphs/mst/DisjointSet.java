package com.animatinator.practice.graphs.mst;

import java.util.HashMap;
import java.util.Map;

class DisjointSet<T> {
    private class DisjointSetNode {
        final T value;

        DisjointSetNode parent;
        int rank;

        DisjointSetNode(T value) {
            this.value = value;
            parent = null;
            rank = 0;
        }
    }

    private Map<T, DisjointSetNode> nodeMap = new HashMap<>();

    void add(T element) {
        DisjointSetNode node = new DisjointSetNode(element);
        nodeMap.put(element, node);
    }

    void union(T first, T second) {
        DisjointSetNode firstParent = find(first);
        DisjointSetNode secondParent = find(second);

        if (firstParent == secondParent || firstParent == null || secondParent == null) {
            return;
        }

        // Order-by-rank.
        if (firstParent.rank < secondParent.rank) {
            makeFirstChildOfSecond(firstParent, secondParent);
        } else {
            makeFirstChildOfSecond(secondParent, firstParent);
        }
    }

    boolean sameSet(T first, T second) {
        return find(first) == find(second);
    }

    private DisjointSetNode find(T element) {
        if (!nodeMap.containsKey(element)) {
            return null;
        }

        DisjointSetNode node = nodeMap.get(element);
        DisjointSetNode cur = node;

        while (cur.parent != null) {
            cur = cur.parent;
        }

        // Path compression.
        if (node != cur) {
            node.parent = cur;
        }

        return cur;
    }

    private void makeFirstChildOfSecond(DisjointSetNode first, DisjointSetNode second) {
        first.parent = second;
        second.rank = Math.max(first.rank + 1, second.rank);
    }
}
