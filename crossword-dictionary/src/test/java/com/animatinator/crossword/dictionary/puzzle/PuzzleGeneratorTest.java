package com.animatinator.crossword.dictionary.puzzle;

import com.animatinator.crossword.dictionary.match.WordMatcher;
import com.animatinator.crossword.dictionary.processed.DictionaryEntry;
import com.animatinator.crossword.dictionary.processed.ProcessedDictionary;
import com.animatinator.crossword.dictionary.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class PuzzleGeneratorTest {
    private PuzzleGenerator generator;
    private ProcessedDictionary dictionary;
    private WordMatcher matcher;

    @Before
    public void setUp() {
        try {
            dictionary = TestUtils.loadTestDictionary();
        } catch (IOException e) {
            fail("Couldn't load test dictionary! Exception: "+e);
        }
        generator = new PuzzleGenerator(dictionary);
        matcher = new WordMatcher();
    }

    @Test
    public void puzzleOfLength() {
        PuzzleConfiguration puzzle = generator.buildPuzzle(4);
        assertEquals(4, puzzle.getNumberOfLettersRequired());
    }

    @Test
    public void puzzleWithNoLength() {
        PuzzleConfiguration puzzle = generator.buildPuzzle(0);
        TestUtils.assertHasLength(0, puzzle.getWords());
    }

    @Test
    public void puzzlesGeneratedAreValid() {
        // Run fifty times to account for randomness to some extent.
        for (int i = 0; i < 50; i++) {
            PuzzleConfiguration puzzle = generator.buildPuzzle(6);
            TestUtils.assertHasLength(14, puzzle.getWords());
            assertWordsAllInDictionary(puzzle.getWords(), dictionary);
            assertWordsAllFormableFromBaseWord(puzzle.getWords(), getLongestWord(puzzle));
        }
    }

    private void assertWordsAllInDictionary(List<String> words, ProcessedDictionary dictionary) {
        List<String> dictionaryWords = dictionary.getDictionary().stream().map(DictionaryEntry::word).collect(Collectors.toList());
        TestUtils.assertContains(dictionaryWords, words);
    }

    private void assertWordsAllFormableFromBaseWord(List<String> words, String baseWord) {
        for (String word : words) {
            assertTrue(matcher.firstWordCanFormSecond(baseWord, word));
        }
    }

    @SuppressWarnings("ConstantConditions")
    private String getLongestWord(PuzzleConfiguration puzzle) {
        return puzzle.getWords().stream().max(Comparator.comparingInt(String::length)).get();
    }
}