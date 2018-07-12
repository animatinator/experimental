package com.animatinator.crossword.dictionary.fingerprint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FingerPrinterTest {
    @Test
    public void emptyFingerPrint() {
        assertEquals("", FingerPrinter.getFingerprint("").toString());
    }

    @Test
    public void simpleFingerPrint() {
        assertEquals("estt", FingerPrinter.getFingerprint("test").toString());
    }

    /**
     * Check that there's a sensible ordering.
     */
    @Test
    public void spanishWords() {
        assertEquals("aeinnrtté", FingerPrinter.getFingerprint("intentaré").toString());
        assertEquals("cmoí", FingerPrinter.getFingerprint("comí").toString());
        assertEquals("inoñ", FingerPrinter.getFingerprint("niño").toString());
        assertEquals("éíñ", FingerPrinter.getFingerprint("ñéí").toString());
    }

    @Test
    public void equalFingerPrints() {
        assertEquals(FingerPrinter.getFingerprint("cause"), FingerPrinter.getFingerprint("sauce"));
    }
}