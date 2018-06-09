package com.animatinator.index.normalise;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class StringNormaliserImplTest {
    private StringNormaliserImpl stringNormaliser;

    @Before
    public void setUp() {
        stringNormaliser = new StringNormaliserImpl();
    }

    @Test
    public void leavesEmptyString() {
        assertEquals("", stringNormaliser.normaliseString(""));
    }

    @Test
    public void removesCapitals() {
        assertEquals("test", stringNormaliser.normaliseString("Test"));
    }

    @Test
    public void stripsSpaces() {
        assertEquals("test", stringNormaliser.normaliseString(" test"));
    }

    @Test
    public void stripsPlainWhitespace() {
        assertEquals("", stringNormaliser.normaliseString("     "));
    }

    @Test
    public void stripsPunctuation() {
        assertEquals("test", stringNormaliser.normaliseString(".te':s,t;"));
    }
}
