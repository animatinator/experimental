package com.animatinator.crossword.dictionary.puzzle;

import com.animatinator.crossword.dictionary.match.WordMatcher;
import com.animatinator.crossword.dictionary.processed.ProcessedDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class PuzzleGenerator {

    private static final PuzzleConfiguration EMPTY_PUZZLE = new PuzzleConfiguration(new ArrayList<>(), 0);

    private final ProcessedDictionary dictionary;
    private final Random random;
    private final WordMatcher matcher;

    public PuzzleGenerator(ProcessedDictionary dictionary) {
        this.dictionary = dictionary;
        random  = new Random();
        matcher = new WordMatcher();
    }

    public PuzzleConfiguration buildPuzzle(int numLetters) {
        if (numLetters == 0) {
            return EMPTY_PUZZLE;
        }

        Optional<String> baseWord = chooseBaseWord(numLetters);
        if (!baseWord.isPresent()) {
            return EMPTY_PUZZLE;
        }

        List<String> words = matcher.getWordsFormableFromWord(baseWord.get(), dictionary);
        return new PuzzleConfiguration(words, numLetters);
    }

    // TODO: if there's nothing of the supplied size, keep trying smaller.
    private Optional<String> chooseBaseWord(int numLetters) {
        List<String> possibleBaseWords = dictionary.getWordsOfLength(numLetters);

        if (possibleBaseWords.size() == 0) {
            return Optional.empty();
        }

        int indexToUse = random.nextInt(possibleBaseWords.size());
        return Optional.of(possibleBaseWords.get(indexToUse));
    }
}
