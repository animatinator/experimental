package com.animatinator.practice.graphs.rottenoranges;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class RottenOrangesTest {
    @Test
    public void simpleCase() {
        int[][] basic = new int[][] {
                {1, 2},
                {2, 2},
                {2, 2}
        };
        assertEquals(3, RottenOranges.timeToRot(basic));
    }

    @Test
    public void impossibleCase() {
        int[][] impossible = new int[][] {
                {1, 0, 2},
                {0, 2, 2},
                {2, 2, 2}
        };
        assertEquals(-1, RottenOranges.timeToRot(impossible));
    }

    @Test
    public void complicated() {
        int[][] complicated = new int[][] {
                {1, 0, 2},
                {2, 2, 2},
                {2, 0, 2},
                {0, 0, 2},
                {2, 2, 2}
        };
        assertEquals(8, RottenOranges.timeToRot(complicated));
    }
}