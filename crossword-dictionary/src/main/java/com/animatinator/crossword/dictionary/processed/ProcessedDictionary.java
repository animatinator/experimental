package com.animatinator.crossword.dictionary.processed;

import com.animatinator.crossword.dictionary.fingerprint.FingerPrinter;
import com.animatinator.crossword.dictionary.fingerprint.WordFingerPrint;

import java.util.ArrayList;
import java.util.List;

public class ProcessedDictionary {
    private ArrayList<List<String>> wordsOfLength = new ArrayList<>();
    private ArrayList<DictionaryEntry> processedDictionary = new ArrayList<>();

    public void addWord(String word) {
        addWordToWordsOfLength(word);
        addWordToProcessedDictionary(word);
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

    private void addWordToProcessedDictionary(String word) {
        WordFingerPrint fingerPrint = FingerPrinter.getFingerprint(word);
        processedDictionary.add(new DictionaryEntry(word, fingerPrint));
    }

    public List<DictionaryEntry> getDictionary() {
        return processedDictionary;
    }

    public List<String> getWordsOfLength(int length) {
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
