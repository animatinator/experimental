package com.animatinator.crossword.dictionary.puzzle;

import java.util.List;

public class PuzzleConfiguration {
    private final List<String> words;
    private final int numberOfLettersRequired;

    public PuzzleConfiguration(List<String> words, int numberOfLettersRequired) {
        this.words = words;
        this.numberOfLettersRequired = numberOfLettersRequired;
    }

    public List<String> getWords() {
        return words;
    }

    public int getNumberOfLettersRequired() {
        return numberOfLettersRequired;
    }
}
