package com.animatinator.practice.dp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class EditDistanceTest {
    @Test
    public void editDistance() {
        assertEquals(1, EditDistance.getMinNumEdits("a", "b"));
        assertEquals(2, EditDistance.getMinNumEdits("aa", "bb"));
        assertEquals(8, EditDistance.getMinNumEdits("carthorse", "orchestra"));
        assertEquals(3, EditDistance.getMinNumEdits("dbc", "abcde"));
    }
}