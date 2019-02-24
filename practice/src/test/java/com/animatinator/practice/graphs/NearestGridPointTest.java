package com.animatinator.practice.graphs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class NearestGridPointTest {
    @Test(expected = IllegalArgumentException.class)
    public void emptyGrid_throws() {
        NearestGridPoint.nearestNonZero(new int[][]{}, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyGridRows_throws() {
        NearestGridPoint.nearestNonZero(new int[][]{{}, {}, {}}, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pointOutOfRange_throws() {
        NearestGridPoint.nearestNonZero(new int[][]{{1}, {2}}, 1, 1);
    }

    @Test
    public void simple() {
        int[][] simpleGrid = new int[][] {
                {0, 0, 1},
                {0, 0, 0},
                {0, 0, 0}
        };

        assertEquals(1, NearestGridPoint.nearestNonZero(simpleGrid, 1, 1));
    }

    @Test
    public void multipleOptions_chooseNearest() {
        int[][] grid = new int[][] {
                {5, 0, 0, 0, 0},
                {0, 0, 0, 7, 0},
                {0, 0, 0, 0, 0},
                {8, 0, 0, 0, 9},
                {0, 7, 0, 0, 0}
        };

        assertEquals(7, NearestGridPoint.nearestNonZero(grid, 2, 2));
    }

    @Test
    public void longDiagonal() {
        int[][] grid = new int[][] {
                {3, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };

        assertEquals(3, NearestGridPoint.nearestNonZero(grid, 2, 2));
    }

    @Test
    public void equalX() {
        int[][] grid = new int[][] {
                {0, 5, 0},
                {0, 0, 0},
                {0, 0, 0},
        };

        assertEquals(5, NearestGridPoint.nearestNonZero(grid, 2, 2));
    }

    @Test
    public void equalY() {
        int[][] grid = new int[][] {
                {0, 0, 0},
                {0, 0, 8},
                {0, 0, 0},
        };

        assertEquals(8, NearestGridPoint.nearestNonZero(grid, 2, 2));
    }
}