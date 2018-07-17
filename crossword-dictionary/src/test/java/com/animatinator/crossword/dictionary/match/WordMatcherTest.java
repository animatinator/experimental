package com.animatinator.crossword.dictionary.match;

import com.animatinator.crossword.dictionary.fingerprint.FingerPrinter;
import com.animatinator.crossword.dictionary.fingerprint.WordFingerPrint;
import com.animatinator.crossword.dictionary.processed.ProcessedDictionary;
import com.animatinator.crossword.dictionary.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordMatcherTest {
    private WordMatcher matcher;
    private ProcessedDictionary dictionary;

    @Before
    public void setUpMatcher() {
        matcher = new WordMatcher();
    }

    @Before
    public void setUpDictionary() {
        dictionary = new ProcessedDictionary();

        String rawDictionary;
        try {
            rawDictionary = new String(Files.readAllBytes(Paths.get("data\\basicdict.txt")));
        } catch (IOException e) {
            fail("Couldn't load test dictionary! Exception: "+e);
            return;
        }
        for (String word : rawDictionary.split(System.getProperty("line.separator"))) {
            dictionary.addWord(word);
        }
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

    @Test
    public void wordMatchInDictionary_emptyWord() {
        List<String> wordsFormable = matcher.getWordsFormableFromWord("", dictionary);
        TestUtils.assertHasLength(0, wordsFormable);
    }

    @Test
    public void wordMatchInDictionary_masterWord() {
        List<String> wordsFormable = matcher.getWordsFormableFromWord("caused", dictionary);
        TestUtils.assertHasLength(14, wordsFormable);
    }

    @Test
    public void wordMatchInDictionary_smallWord() {
        List<String> wordsFormable = matcher.getWordsFormableFromWord("aces", dictionary);
        TestUtils.assertContains(wordsFormable, "aces", "case", "sea");
    }

    @Test
    public void wordMatchInDictionary_irrelevantWord() {
        List<String> wordsFormable = matcher.getWordsFormableFromWord("zoo", dictionary);
        TestUtils.assertHasLength(0, wordsFormable);
    }

    @Test
    public void wordMatchInDictionary_extraLongMasterWord() {
        List<String> wordsFormable = matcher.getWordsFormableFromWord("causedational", dictionary);
        TestUtils.assertHasLength(14, wordsFormable);
    }

    private boolean firstWordCanFormSecond(String first, String second) {
        WordFingerPrint firstPrint = FingerPrinter.getFingerprint(first);
        WordFingerPrint secondPrint = FingerPrinter.getFingerprint(second);

        return WordMatcher.firstWordCanFormSecond(firstPrint, secondPrint);
    }
}