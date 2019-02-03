package com.animatinator.practice.graphs.dijkstra;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class DijkstraTest {
    @Test
    public void simpleTest() {
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Node n4 = new Node();

        Node.joinNodes(n1, n2, 1);
        Node.joinNodes(n1, n3, 5);
        Node.joinNodes(n2, n4, 10);
        Node.joinNodes(n3, n4, 5);

        Dijkstra.runDijkstra(Arrays.asList(n1, n2, n3, n4), n1);
        assertEquals(10, n4.dist);
        assertEquals(n3, n4.predecessor);
        assertEquals(n1, n3.predecessor);
    }
}