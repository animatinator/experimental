package com.animatinator.practice.graphs.mst;

class Node {
    private static int NEXT_ID = 0;

    private final int id;

    Node() {
        id = NEXT_ID++;
    }
}
