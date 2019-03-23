package com.animatinator.practice.rotatedsearch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FindInRotatedArrayTest {
    @Test
    public void example() {
        int[] array = new int[] {3, 4, 5, 6, 7, 0, 1, 2};

        assertEquals(4, FindInRotatedArray.findInArray(array, 7));
        assertEquals(-1, FindInRotatedArray.findInArray(array, 8));
    }

    @Test
    public void notRotated() {
        int[] array = new int[] {1, 2, 3, 4, 5};

        assertEquals(2, FindInRotatedArray.findInArray(array, 3));
    }
}