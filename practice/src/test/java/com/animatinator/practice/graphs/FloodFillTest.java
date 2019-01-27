package com.animatinator.practice.graphs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class FloodFillTest {
    @Test
    public void outOfRange() {
        boolean[][] world = new boolean[][] {{false}};

        try {
            FloodFill.floodFill(world, 1, 1);
        } catch (AssertionError expected) {
            return;
        }

        fail("Assertion should have failed");
    }

    @Test
    public void worldHasNoDepth() {
        boolean[][] world = new boolean[][] {};

        try {
            FloodFill.floodFill(world, 0, 0);
        } catch (AssertionError expected) {
            return;
        }

        fail("Assertion should have failed");
    }

    @Test
    public void singleCell() {
        boolean[][] world = new boolean[][] {{false}};
        boolean[][] expected = new boolean[][] {{true}};

        FloodFill.floodFill(world, 0, 0);

        assertEquals(expected, world);
    }

    @Test
    public void moreComplex() {
        boolean[][] world = new boolean[][] {
                {true, true, true, true, true},
                {true, true, false, true, true},
                {true, false, false, false, true},
                {false, true, false, true, true},
                {true, true, true, true, true},
        };
        boolean[][] expected = new boolean[][] {
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {false, true, true, true, true},
                {true, true, true, true, true},
        };

        FloodFill.floodFill(world, 2, 2);

        assertEquals(expected, world);
    }

    @Test
    public void moreComplex_offCentre() {
        boolean[][] world = new boolean[][] {
                {true, true, true, true, true},
                {true, true, false, true, true},
                {true, false, false, false, true},
                {false, true, false, true, true},
                {true, true, true, true, true},
        };
        boolean[][] expected = new boolean[][] {
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {false, true, true, true, true},
                {true, true, true, true, true},
        };

        FloodFill.floodFill(world, 2, 3);

        assertEquals(expected, world);
    }

    @Test
    public void chequerBoard() {
        boolean[][] world = new boolean[][] {
                {true, false, true},
                {false, true, false},
                {true, false, true},
        };
        boolean[][] expected = new boolean[][] {
                {true, false, true},
                {false, false, false},
                {true, false, true},
        };

        FloodFill.floodFill(world, 1, 1);

        assertEquals(expected, world);
    }
}