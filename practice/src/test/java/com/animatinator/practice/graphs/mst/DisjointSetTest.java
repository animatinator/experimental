package com.animatinator.practice.graphs.mst;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class DisjointSetTest {
    @Test
    public void newElementsAreDisjoint() {
        DisjointSet<Integer> set = new DisjointSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        assertFalse(set.sameSet(1, 2));
        assertFalse(set.sameSet(2, 3));
        assertFalse(set.sameSet(1, 3));
    }

    @Test
    public void unionWorksInBothDirections() {
        DisjointSet<Integer> set = new DisjointSet<>();
        set.add(1);
        set.add(2);

        set.union(1, 2);

        assertTrue(set.sameSet(1, 2));
        assertTrue(set.sameSet(2, 1));
    }

    @Test
    public void disjointUnions() {
        DisjointSet<Integer> set = new DisjointSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);

        set.union(1, 2);
        set.union(3, 4);

        assertTrue(set.sameSet(1, 2));
        assertTrue(set.sameSet(4, 3));
        assertFalse(set.sameSet(1, 3));
        assertFalse(set.sameSet(4, 2));
    }

    @Test
    public void unionsStack() {
        DisjointSet<Integer> set = new DisjointSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);

        set.union(1, 2);
        set.union(3, 4);
        set.union(2, 4);

        assertTrue(set.sameSet(1, 3));
    }
}