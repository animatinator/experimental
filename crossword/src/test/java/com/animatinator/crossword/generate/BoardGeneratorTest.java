package com.animatinator.crossword.generate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

@RunWith(JUnit4.class)
public class BoardGeneratorTest {
    @Test
    public void generateBoard() {
        new BoardGenerator().generateBoard(Arrays.asList("test", "words", "one", "gigantic"));
    }
}