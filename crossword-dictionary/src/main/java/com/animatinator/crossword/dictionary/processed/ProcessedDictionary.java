package com.animatinator.crossword.dictionary.processed;

import com.animatinator.crossword.dictionary.fingerprint.FingerPrinter;
import com.animatinator.crossword.dictionary.fingerprint.WordFingerPrint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessedDictionary {
    private ArrayList<List<String>> wordsOfLength = new ArrayList<>();
    private Map<WordFingerPrint, List<String>> fingerPrintMap = new HashMap<>();

    public void addWord(String word) {
        addWordToWordsOfLength(word);
        addWordToFingerPrintMap(word);
    }

    private void addWordToWordsOfLength(String word) {
        int length = word.length();

        if (length < wordsOfLength.size() && wordsOfLength.get(length) != null) {
            wordsOfLength.get(length).add(word);
        } else {
            expandToLength(length);
            List<String> newEntry = new ArrayList<>();
            newEntry.add(word);
            wordsOfLength.add(length, newEntry);
        }
    }

    private void addWordToFingerPrintMap(String word) {
        WordFingerPrint fingerPrint = FingerPrinter.getFingerprint(word);

        if (fingerPrintMap.containsKey(fingerPrint)) {
            fingerPrintMap.get(fingerPrint).add(word);
        } else {
            List<String> newEntry = new ArrayList<>();
            newEntry.add(word);
            fingerPrintMap.put(fingerPrint, newEntry);
        }
    }

    List<String> getWordsMatchingFingerprint(String word) {
        WordFingerPrint fingerPrint = FingerPrinter.getFingerprint(word);

        if (fingerPrintMap.containsKey(fingerPrint)) {
            return fingerPrintMap.get(FingerPrinter.getFingerprint(word));
        } else {
            return new ArrayList<>();
        }
    }

    List<String> getWordsOfLength(int length) {
        if (length < wordsOfLength.size()) {
            return wordsOfLength.get(length);
        }

        return new ArrayList<>();
    }

    private void expandToLength(int length) {
        for (int i = wordsOfLength.size(); i < length; i++) {
            wordsOfLength.add(new ArrayList<>());
        }
        wordsOfLength.ensureCapacity(length);
    }
}
