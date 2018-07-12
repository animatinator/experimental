package com.animatinator.crossword.dictionary.processed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ProcessedDictionaryTest {
    private ProcessedDictionary dictionary = new ProcessedDictionary();

    @Test
    public void noWordsOfLength() {
        assertEmpty(dictionary.getWordsOfLength(0));
        assertEmpty(dictionary.getWordsOfLength(1));
        assertEmpty(dictionary.getWordsOfLength(50));
    }

    @Test
    public void addWordOfLength() {
        dictionary.addWord("hello");
        List<String> wordsOfLengthFive = dictionary.getWordsOfLength(5);
        assertHasLength(1, wordsOfLengthFive);
        assertContains(wordsOfLengthFive, "hello");
    }

    @Test
    public void addWordOfLengthInExistingRange() {
        dictionary.addWord("hello");
        dictionary.addWord("one");
        assertHasLength(1, dictionary.getWordsOfLength(3));
        assertHasLength(1, dictionary.getWordsOfLength(5));
    }

    @Test
    public void addTwoWordsOfSameLength() {
        dictionary.addWord("word");
        dictionary.addWord("four");
        List<String> wordsOfLengthFour = dictionary.getWordsOfLength(4);
        assertHasLength(2, wordsOfLengthFour);
        assertContains(wordsOfLengthFour, "word", "four");
    }

    /**
     * Not an important case - just verifying an edge case.
     */
    @Test
    public void wordOfLengthZero() {
        dictionary.addWord("");
        List<String> wordsOfLengthZero = dictionary.getWordsOfLength(0);
        assertHasLength(1, wordsOfLengthZero);
        assertContains(wordsOfLengthZero, "");
    }

    @Test
    public void eAcute() {
        dictionary.addWord("intentaré");
        List<String> wordsOfLengthNine = dictionary.getWordsOfLength(9);
        assertHasLength(1, wordsOfLengthNine);
        assertContains(wordsOfLengthNine, "intentaré");
    }

    @Test
    public void iAcute() {
        dictionary.addWord("comí");
        List<String> wordsOfLengthFour = dictionary.getWordsOfLength(4);
        assertHasLength(1, wordsOfLengthFour);
        assertContains(wordsOfLengthFour, "comí");
    }

    @Test
    public void enye() {
        dictionary.addWord("niño");
        List<String> wordsOfLengthFour = dictionary.getWordsOfLength(4);
        assertHasLength(1, wordsOfLengthFour);
        assertContains(wordsOfLengthFour, "niño");
    }

    private static <T> void assertEmpty(List<T> list) {
        assertTrue(list.isEmpty());
    }

    private static <T> void assertHasLength(int expected, List<T> list) {
        assertEquals(expected, list.size());
    }

    @SafeVarargs
    private static <T> void assertContains(List<T> list, T... items) {
        assertTrue(list.containsAll(Arrays.asList(items)));
    }
}