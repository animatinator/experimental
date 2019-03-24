package com.animatinator.practice.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertArrayEquals;

@RunWith(JUnit4.class)
public class MatrixLightingTest {
    @Test
    public void firstExample() {
        int[][] example = new int[][] {
                {1, 0},
                {0, 0}};
        int[][] expected = new int[][] {
                {1, 1},
                {1, 0}};
        assertArrayEquals(expected, MatrixLighting.lightMatrixFromPoints(example));
    }

    @Test
    public void secondExample() {
        int[][] example = new int[][] {
                {0, 0, 0},
                {0, 0, 1}};
        int[][] expected = new int[][] {
                {0, 0, 1},
                {1, 1, 1}};
        assertArrayEquals(expected, MatrixLighting.lightMatrixFromPoints(example));
    }

    @Test
    public void thirdExample() {
        int[][] example = new int[][] {
                {1, 0, 0},
                {1, 0, 0},
                {1, 0, 0},
                {0, 0, 0}};
        int[][] expected = new int[][] {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 0, 0}};
        assertArrayEquals(expected, MatrixLighting.lightMatrixFromPoints(example));
    }
}