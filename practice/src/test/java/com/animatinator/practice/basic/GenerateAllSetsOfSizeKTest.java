package com.animatinator.practice.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class GenerateAllSetsOfSizeKTest {
    @Test
    public void simpleTest() {
        List<List<Integer>> result = GenerateAllSetsOfSizeK.generateAllSetsOfSizeK(2, 5);
        assertNotNull(result);
        assertEquals(10, result.size());
    }
}