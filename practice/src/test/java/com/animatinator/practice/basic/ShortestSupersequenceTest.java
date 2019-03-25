package com.animatinator.practice.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ShortestSupersequenceTest {
    @Test
    public void equal() {
        assertEquals(4, ShortestSupersequence.shortestSupersequence("abcd", "abcd"));
    }

    @Test
    public void oneOff() {
        assertEquals(5, ShortestSupersequence.shortestSupersequence("abcd", "abce"));
    }

    @Test
    public void anotherOneOff() {
        assertEquals(5, ShortestSupersequence.shortestSupersequence("abcd", "abed"));
    }

    @Test
    public void example() {
        assertEquals(6, ShortestSupersequence.shortestSupersequence("abcd", "xycd"));
    }
    @Test
    public void secondExample() {
        assertEquals(6, ShortestSupersequence.shortestSupersequence("efgh", "jghi"));
    }
}