package com.animatinator.practice.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BinarySearchTest {
    @Test
    public void binarySearch_empty() {
        assertEquals(-1, BinarySearch.findElement(new int[] {}, 3));
    }

    @Test
    public void binarySearch_singleton_notPresent() {
        assertEquals(-1, BinarySearch.findElement(new int[] {4}, 3));
    }

    @Test
    public void binarySearch_singeton_present() {
        assertEquals(0, BinarySearch.findElement(new int[] {3}, 3));
    }

    @Test
    public void binarySearch_oddArray() {
        assertEquals(2, BinarySearch.findElement(new int[] {1, 2, 3, 4, 5}, 3));
    }

    @Test
    public void binarySearch_evenArray() {
        assertEquals(2, BinarySearch.findElement(new int[] {1, 2, 3, 4}, 3));
    }

    @Test
    public void binarySearch_atStart() {
        assertEquals(0, BinarySearch.findElement(new int[] {1, 2, 3, 4, 5}, 1));
    }

    @Test
    public void binarySearch_atEnd() {
        assertEquals(4, BinarySearch.findElement(new int[] {1, 2, 3, 4, 5}, 5));
    }
}