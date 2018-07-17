package com.animatinator.crossword.dictionary.puzzle;

import com.animatinator.crossword.dictionary.match.WordMatcher;
import com.animatinator.crossword.dictionary.processed.ProcessedDictionary;

import java.util.*;
import java.util.stream.Collectors;

public class PuzzleGenerator {

    private static final PuzzleConfiguration EMPTY_PUZZLE = new PuzzleConfiguration(new ArrayList<>(), 0);

    private final int minimumWordLength;
    private final int maximumWordCount;

    private final ProcessedDictionary dictionary;
    private final Random random;
    private final WordMatcher matcher;

    public PuzzleGenerator(ProcessedDictionary dictionary) {
        this(dictionary, 0, 1000);
    }

    private PuzzleGenerator(ProcessedDictionary dictionary, int minimumWordLength, int maximumWordCount) {
        this.dictionary = dictionary;
        this.minimumWordLength = minimumWordLength;
        this.maximumWordCount = maximumWordCount;
        random  = new Random();
        matcher = new WordMatcher();
    }

    public PuzzleGenerator withMinimumWordLength(int newMinimumWordLength) {
        return new PuzzleGenerator(dictionary, newMinimumWordLength, maximumWordCount);
    }

    public PuzzleGenerator withMaximumWordCount(int newMaximumWordCount) {
        return new PuzzleGenerator(dictionary, minimumWordLength, newMaximumWordCount);
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

        words = words.stream().filter(word -> word.length() >= minimumWordLength).collect(Collectors.toList());

        if (maximumWordCount < words.size()) {
            words = randomlySelectNFromList(words, maximumWordCount);
        }

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

    private List<String> randomlySelectNFromList(List<String> input, int limit) {
        List<String> copy = new LinkedList<>(input);
        Collections.shuffle(copy);
        return copy.subList(0, Math.min(input.size(), limit));
    }
}
