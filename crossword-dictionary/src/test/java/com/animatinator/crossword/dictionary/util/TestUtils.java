package com.animatinator.crossword.dictionary.util;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUtils {
    public static <T> void assertEmpty(List<T> list) {
        assertTrue(list.isEmpty());
    }

    public static <T> void assertHasLength(int expected, List<T> list) {
        assertEquals(expected, list.size());
    }

    @SafeVarargs
    public static <T> void assertContains(List<T> list, T... items) {
        assertTrue(list.containsAll(Arrays.asList(items)));
    }
}
