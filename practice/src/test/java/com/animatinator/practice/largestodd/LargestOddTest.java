package com.animatinator.practice.largestodd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class LargestOddTest {
    @Test
    public void simpleTest() {
        int[][] matrix = new int[][] {
                {2, 4, 5},
                {9, 6, 2},
                {1, 2, 8}
        };

        assertEquals(825, LargestOdd.findLargestOddNumberAboveTenAlongMatrixRow(matrix));
    }

    @Test
    public void edgesAllEven() {
        int[][] matrix = new int[][] {
                {2, 4, 6},
                {6, 5, 2},
                {8, 2, 8}
        };

        assertEquals(85, LargestOdd.findLargestOddNumberAboveTenAlongMatrixRow(matrix));
    }
}