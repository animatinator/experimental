package com.animatinator.practice.graphs.bellmanford;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class BellmanFordTest {
    @Test(expected = AssertionError.class)
    public void vertexNotContained() throws BellmanFord.CycleException {
        BellmanFord.runBellmanFord(new Graph(new HashSet<>(), new HashSet<>()), new Vertex());
    }

    @Test
    public void noEdges_verticesUnchanged() throws BellmanFord.CycleException {
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Vertex v3 = new Vertex();
        Set<Vertex> vertices = new HashSet<>(Arrays.asList(v1, v2, v3));
        Set<Edge> edges = new HashSet<>();

        BellmanFord.runBellmanFord(new Graph(vertices, edges), v1);

        assertEquals(0.0, v1.dist, 0.1);
        assertEquals(v2.dist, Integer.MAX_VALUE, 1);
        assertNull(v2.predecessor);
        assertEquals(v3.dist, Integer.MAX_VALUE, 1);
        assertNull(v3.predecessor);
    }

    @Test
    public void sensibleCase() throws BellmanFord.CycleException {
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Vertex v3 = new Vertex();
        Vertex v4 = new Vertex();
        Set<Vertex> vertices = new HashSet<>(Arrays.asList(v1, v2, v3, v4));

        Edge e1 = new Edge(v1, v2, 1.0f);
        Edge e2 = new Edge(v1, v3, 100.0f);
        Edge e3 = new Edge(v2, v4, 100.0f);
        Edge e4 = new Edge(v3, v4, 1.0f);
        Edge e5 = new Edge(v2, v3, 1.0f);
        Set<Edge> edges = new HashSet<>(Arrays.asList(e1, e2, e3, e4, e5));

        BellmanFord.runBellmanFord(new Graph(vertices, edges), v1);

        assertEquals(3.0, v4.dist, 0.1);
        assertEquals(v3, v4.predecessor);
        assertEquals(v2, v3.predecessor);
        assertEquals(v1, v2.predecessor);
    }

    @Test(expected = BellmanFord.CycleException.class)
    public void negativeCycle() throws BellmanFord.CycleException {
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Vertex v3 = new Vertex();
        Set<Vertex> vertices = new HashSet<>(Arrays.asList(v1, v2, v3));

        Edge e1 = new Edge(v1, v2, -1.0f);
        Edge e2 = new Edge(v2, v3, -1.0f);
        Edge e3 = new Edge(v3, v1, -1.0f);
        Set<Edge> edges = new HashSet<>(Arrays.asList(e1, e2, e3));

        BellmanFord.runBellmanFord(new Graph(vertices, edges), v1);
    }
}