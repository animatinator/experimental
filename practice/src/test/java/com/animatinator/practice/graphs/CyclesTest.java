package com.animatinator.practice.graphs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class CyclesTest {
    @Test
    public void noCycle_simple() {
        GraphNode<Boolean> a = new GraphNode<>(true);
        GraphNode<Boolean> b = new GraphNode<>(true);
        GraphNode<Boolean> c = new GraphNode<>(true);
        GraphNode<Boolean> d = new GraphNode<>(true);

        GraphNode.linkNodes(a, b);
        GraphNode.linkNodes(b, c);
        GraphNode.linkNodes(b, d);

        assertFalse(Cycles.hasCycle(a));
    }

    @Test
    public void cycle_simple() {
        GraphNode<Boolean> a = new GraphNode<>(true);
        GraphNode<Boolean> b = new GraphNode<>(true);
        GraphNode<Boolean> c = new GraphNode<>(true);

        GraphNode.linkNodes(a, b);
        GraphNode.linkNodes(b, c);
        GraphNode.linkNodes(c, a);

        assertTrue(Cycles.hasCycle(a));
    }

    @Test
    public void cycle() {
        GraphNode<Boolean> a = new GraphNode<>(true);
        GraphNode<Boolean> b = new GraphNode<>(true);
        GraphNode<Boolean> c = new GraphNode<>(true);
        GraphNode<Boolean> d = new GraphNode<>(true);

        GraphNode.linkNodes(a, b);
        GraphNode.linkNodes(b, c);
        GraphNode.linkNodes(b, d);
        GraphNode.linkNodes(c, d);

        assertTrue(Cycles.hasCycle(a));
    }
}