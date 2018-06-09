package com.animatinator.index.indexer;

import com.animatinator.index.files.File;
import com.animatinator.index.indexer.HashMapFileIndex;
import com.animatinator.index.indexer.match.FileMatchImpl;
import com.animatinator.index.indexer.search.IndexSearcher;
import com.animatinator.index.normalise.StringNormaliser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileIndexer {
    private final StringNormaliser stringNormaliser;
    private final HashMapFileIndex fileIndex;

    public FileIndexer(StringNormaliser stringNormaliser) {
        this.stringNormaliser = stringNormaliser;
        fileIndex = new HashMapFileIndex(stringNormaliser);
    }

    void addFileToIndex(File file) {
        String name = file.getName();
        String contents = file.getContents();

        Map<String, Integer> wordCounts = new HashMap<>();

        for (String rawWord : contents.split("\\s")) {
            String normalisedWord = stringNormaliser.normaliseString(rawWord);
            incrementCount(normalisedWord, wordCounts);
        }

        for (String word : wordCounts.keySet()) {
            fileIndex.addMatch(word, new FileMatchImpl(name, contents, wordCounts.get(word)));
        }
    }

    private void incrementCount(String normalisedWord, Map<String, Integer> wordCounts) {
        if (wordCounts.containsKey(normalisedWord)) {
            wordCounts.put(normalisedWord, wordCounts.get(normalisedWord) + 1);
        } else {
            wordCounts.put(normalisedWord, 1);
        }
    }

    public void addFilesToIndex(List<File> files) {
        for (File file : files) {
            addFileToIndex(file);
        }
    }

    public IndexSearcher getSearcher() {
        return fileIndex.getSearcher();
    }
}
