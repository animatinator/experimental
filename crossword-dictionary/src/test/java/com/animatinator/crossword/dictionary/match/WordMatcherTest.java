package com.animatinator.crossword.dictionary.match;

import com.animatinator.crossword.dictionary.fingerprint.FingerPrinter;
import com.animatinator.crossword.dictionary.fingerprint.WordFingerPrint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class WordMatcherTest {
    private WordMatcher matcher;

    @Before
    public void setUpMatcher() {
        matcher = new WordMatcher();
    }

    @Test
    public void emptyWordFormsNothing() {
        assertFalse(firstWordCanFormSecond("", "test"));
    }

    @Test
    public void emptyWordTriviallyFormable() {
        assertTrue(firstWordCanFormSecond("test", ""));
    }

    @Test
    public void equalWordsMatch() {
        assertTrue(firstWordCanFormSecond("test", "test"));
    }

    @Test
    public void firstStringHasMoreDuplicateCharacters() {
        assertTrue(firstWordCanFormSecond("testt", "test"));
    }

    @Test
    public void secondStringHasTooManyOfOneCharacter() {
        assertFalse(firstWordCanFormSecond("test", "teest"));
    }

    @Test
    public void firstStringHasExtraCharacters() {
        assertTrue(firstWordCanFormSecond("toast", "tost"));
    }

    @Test
    public void secondStringHasExtraCharacters() {
        assertFalse(firstWordCanFormSecond("tost", "toast"));
    }

    @Test
    public void casesDoNotMatch() {
        assertTrue(firstWordCanFormSecond("tEsT", "TeSt"));
    }

    @Test
    public void spanishExample() {
        assertTrue(firstWordCanFormSecond("intentaré", "tentar"));
    }

    private boolean firstWordCanFormSecond(String first, String second) {
        WordFingerPrint firstPrint = FingerPrinter.getFingerprint(first);
        WordFingerPrint secondPrint = FingerPrinter.getFingerprint(second);

        return matcher.firstWordCanFormSecond(firstPrint, secondPrint);
    }
}