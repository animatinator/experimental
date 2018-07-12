package com.animatinator.crossword.dictionary.fingerprint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(JUnit4.class)
public class FingerPrinterTest {
    @Test
    public void emptyFingerPrint() {
        assertEquals("", FingerPrinter.getFingerprint("").toString());
    }

    @Test
    public void simpleFingerPrint() {
        assertEquals("est", FingerPrinter.getFingerprint("test").toString());
    }

    /**
     * Check that there's a sensible ordering.
     */
    @Test
    public void spanishWords() {
        assertEquals("aeinrté", FingerPrinter.getFingerprint("intentaré").toString());
        assertEquals("cmoí", FingerPrinter.getFingerprint("comí").toString());
        assertEquals("inoñ", FingerPrinter.getFingerprint("niño").toString());
        assertEquals("éíñ", FingerPrinter.getFingerprint("ñéí").toString());
    }

    @Test
    public void nonMatch() {
        assertNotEquals(FingerPrinter.getFingerprint("hello"), FingerPrinter.getFingerprint("hallo"));
    }

    @Test
    public void equalFingerPrints() {
        assertEquals(FingerPrinter.getFingerprint("cause"), FingerPrinter.getFingerprint("sauce"));
    }

    @Test
    public void duplicateLetters() {
        assertEquals(FingerPrinter.getFingerprint("adder"), FingerPrinter.getFingerprint("read"));
    }
}