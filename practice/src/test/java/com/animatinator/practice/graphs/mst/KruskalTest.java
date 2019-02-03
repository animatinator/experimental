package com.animatinator.practice.graphs.mst;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

@RunWith(JUnit4.class)
public class KruskalTest {
    @Test
    public void simpleTest() {
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Node n4 = new Node();

        Edge cheap1 = new Edge(n1, n2, 1);
        Edge cheap2 = new Edge(n3, n4, 1);
        Edge cheap3 = new Edge(n2, n3, 1);
        Edge cost1 = new Edge(n1, n4, 2);
        Edge cost2 = new Edge(n1, n3, 2);
        Edge cost3 = new Edge(n2, n4, 2);

        List<Edge> result =
                Kruskal.minimumSpanningTree(
                        Arrays.asList(n1, n2, n3, n4),
                        Arrays.asList(cheap1, cheap2, cheap3, cost1, cost2, cost3));

        assertArrayEquals(result.toArray(), new Edge[] {cheap1, cheap2, cheap3});
    }
}