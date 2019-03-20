package com.animatinator.practice.snapshot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SnapshottableArrayTest {
    @Test
    public void getAndSet() {
        SnapshottableArray<Integer> array = new SnapshottableArray<>(3, 0);
        array.set(1, 1);
        array.set(2, 2);

        assertEquals(0, (int)array.get(0));
        assertEquals(1, (int)array.get(1));
        assertEquals(2, (int)array.get(2));
    }

    @Test
    public void snapshot() {
        SnapshottableArray<Integer> array = new SnapshottableArray<>(3, 0);
        array.set(1, 1);
        array.set(2, 2);

        int oldSnapshot = array.snapshot();

        array.set(0, 4);
        array.set(1, 5);
        array.set(2, 6);

        assertEquals(0, (int)array.valueAtTime(oldSnapshot, 0));
        assertEquals(1, (int)array.valueAtTime(0, 1));
        assertEquals(2, (int)array.valueAtTime(0, 2));
        assertEquals(4, (int)array.get(0));
        assertEquals(5, (int)array.get(1));
        assertEquals(6, (int)array.get(2));
    }

    @Test
    public void snapshotWithGap() {
        SnapshottableArray<Integer> array = new SnapshottableArray<>(3, 0);
        array.snapshot();

        array.set(0, 15);
        int secondSnap = array.snapshot();

        array.set(1, 20);

        // Index one wasn't changed in the second snapshot, so should take its value from the earlier snapshot.
        assertEquals(0, (int)array.valueAtTime(secondSnap, 1));
    }
}