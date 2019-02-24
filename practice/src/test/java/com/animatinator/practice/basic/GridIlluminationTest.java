package com.animatinator.practice.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class GridIlluminationTest {
    @Test
    public void simple() {
        assertTrue(Arrays.equals(
                new int[]{1, 0},
                GridIllumination.gridIllumination(5, new int[][]{{0, 0}, {4, 4}}, new int[][]{{1, 1}, {1, 0}})));
    }

    @Test
    public void sanityTesting() {
        int[][] queries = new int[][]{
                {1, 1},
                {9, 9},
                {9, 1},
                {1, 9},
                {5, 1},
                {5, 9},
                {1, 5},
                {9, 5},
                {5, 5},
                {1, 1}
        };

        assertTrue(Arrays.equals(
                new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                GridIllumination.gridIllumination(10, new int[][]{{5, 5}}, queries)));
    }

    @Test
    public void moreComplex() {
        int[][] lamps = new int[][] {
                {7,55}, {53,61}, {2,82}, {67,85}, {81,75}, {38,91}, {68,0}, {60,43}, {40,19}, {12,75}, {26,2}, {24,89},
                {42,81}, {60,58}, {77,72}, {33,24}, {19,93}, {7,16}, {58,54}, {78,57}, {97,49}, {65,16}, {42,75},
                {90,50}, {89,34}, {76,97}, {58,23}, {62,47}, {94,28}, {88,65}, {3,87}, {81,10}, {12,81}, {44,81},
                {54,92}, {90,54}, {17,54}, {27,82}, {48,15}, {8,46}, {4,99}, {15,13}, {90,77}, {2,87}, {18,33}, {52,90},
                {4,95}, {57,61}, {31,22}, {32,8}, {49,26}, {24,65}, {88,55}, {88,38}, {64,76}, {94,76}, {59,12},
                {41,46}, {80,28}, {38,36}, {65,67}, {75,37}, {56,97}, {83,57}, {2,4}, {44,43}, {71,90}, {62,40},
                {79,94}, {81,11}, {96,34}, {38,11}, {22,3}, {54,96}, {78,33}, {54,54}, {79,98}, {1,28}, {0,32}, {37,11}
        };

        int[][] queries = new int[][] {
                {24,84}, {95,68}, {80,35}, {31,53}, {69,45}, {85,29}, {87,25}, {42,47}, {7,59}, {99,3}, {31,70},
                {64,62}, {44,91}, {55,25}, {15,52}, {95,33}, {21,29}, {61,34}, {93,34}, {79,27}, {30,86}, {52,0},
                {18,10}, {5,1}, {40,21}, {11,48}, {55,94}, {22,42}, {81,0}, {39,43}, {5,25}, {43,29}, {45,47}, {83,93},
                {77,70}, {22,63}, {30,73}, {18,48}, {39,88}, {91,47}
        };

        int[] expected = new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,1};

        assertArrayEquals(expected, GridIllumination.gridIllumination(100, lamps, queries));
    }
}