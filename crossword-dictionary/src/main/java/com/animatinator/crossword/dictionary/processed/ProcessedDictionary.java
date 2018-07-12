package com.animatinator.crossword.dictionary.processed;

import java.util.ArrayList;
import java.util.List;

public class ProcessedDictionary {
    private ArrayList<List<String>> wordsOfLength = new ArrayList<>();

    public void addWord(String word) {
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
