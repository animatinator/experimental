package com.animatinator.practice.dp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FindSequenceInArrayTest {
    @Test
    public void exampleCase() {
        int[][] array = new int[][] {
                {'1', '2', '3'},
                {'3', '4', '5'},
                {'5', '6', '7'}
        };

        assertTrue(FindSequenceInArray.containsSequence(array, "1346"));
        assertFalse(FindSequenceInArray.containsSequence(array, "1423"));
    }
}