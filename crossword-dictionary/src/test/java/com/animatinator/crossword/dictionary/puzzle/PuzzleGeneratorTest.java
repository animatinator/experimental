package com.animatinator.crossword.dictionary.puzzle;

import com.animatinator.crossword.dictionary.processed.ProcessedDictionary;
import com.animatinator.crossword.dictionary.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class PuzzleGeneratorTest {
    private PuzzleGenerator generator;
    private ProcessedDictionary dictionary;

    @Before
    public void setUp() {
        try {
            dictionary = TestUtils.loadTestDictionary();
        } catch (IOException e) {
            fail("Couldn't load test dictionary! Exception: "+e);
        }
        generator = new PuzzleGenerator(dictionary);
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
}