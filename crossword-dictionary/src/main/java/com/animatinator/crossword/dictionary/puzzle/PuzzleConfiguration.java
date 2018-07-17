package com.animatinator.crossword.dictionary.puzzle;

import java.util.List;

public class PuzzleConfiguration {
    private final List<String> words;
    private final int numberOfLettersRequired;

    PuzzleConfiguration(List<String> words, int numberOfLettersRequired) {
        this.words = words;
        this.numberOfLettersRequired = numberOfLettersRequired;
    }

    List<String> getWords() {
        return words;
    }

    int getNumberOfLettersRequired() {
        return numberOfLettersRequired;
    }

    @Override
    public String toString() {
        return "PuzzleConfiguration{" +
                "words=" + words +
                '}';
    }
}
