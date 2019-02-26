package com.animatinator.practice.dp.wordwrap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class WordWrapperTest {
    @Test
    public void sanity() {
        WordWrapper.Result result = WordWrapper.wordWrap("Geeks for Geeks presents word wrap problem", 15);
        assertEquals(13, result.cost);
        assertEquals("Geeks for Geeks\npresents word\nwrap problem", result.result);
    }
}