package com.animatinator.practice.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SumOfKIntsTest {
    @Test
    public void singleIntSum() {
        int[] integers = {1, 2, 3, 4, 5};

        List<Integer> result = SumOfKInts.kIntSum(integers, 1, 3);

        assertEquals(1, result.size());
        assertEquals(3, (int)result.get(0));
    }

    @Test
    public void doubleIntSum() {
        int[] integers = {1, 4, 3, 9, 2};

        List<Integer> result = SumOfKInts.kIntSum(integers, 2, 7);

        assertEquals(2, result.size());
        assertArrayEquals(new Integer[] {4, 3}, result.toArray());
    }

    @Test
    public void tripleIntSum() {
        int[] integers = {123, 421, 52, 1, 765, 83};

        List<Integer> result = SumOfKInts.kIntSum(integers, 3, 136);

        assertEquals(3, result.size());
        assertArrayEquals(new Integer[] {83, 52, 1}, result.toArray());
    }

    @Test
    public void totallyWildQuadrupleIntSumGoodGravy() {
        int[] integers = {12, 52, 712, 6, 86, 1235, 273, 80, 9, 1, 4, 6, 21};

        // 273 + 12 + 52 + 1235 = 337 + 1235 = 1572
        List<Integer> result = SumOfKInts.kIntSum(integers, 4, 1572);

        assertEquals(4, result.size());
        assertArrayEquals(new Integer[] {1235, 273, 52, 12}, result.toArray());
    }
}