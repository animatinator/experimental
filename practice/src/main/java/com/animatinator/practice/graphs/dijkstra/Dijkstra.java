package com.animatinator.practice.graphs.dijkstra;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Dijkstra {
    static void runDijkstra(List<Node> nodes, Node startNode) {
        startNode.dist = 0;
        Queue<Node> nodeQueue = new PriorityQueue<>(nodes);

        while (!nodeQueue.isEmpty()) {
            Node current = nodeQueue.poll();
            assert(current != null);

            for (Edge e : current.edges) {
                Node target = e.target;
                if (target.dist > current.dist + e.weight) {
                    target.dist = current.dist + e.weight;
                    target.predecessor = current;
                    updatePriority(nodeQueue, target);
                }
            }
        }
    }

    /**
     * Note: This is dire, and only done because Java lacks a way of decreasing the priority of an element in the
     * priority queue. Cost here: O(n), whilst it would be O(lg(n)).
     */
    private static void updatePriority(Queue<Node> queue, Node node) {
        queue.remove(node);
        queue.add(node);
    }
}
