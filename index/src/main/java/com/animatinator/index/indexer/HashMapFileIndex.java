package com.animatinator.index.indexer;

import com.animatinator.index.indexer.match.FileMatch;
import com.animatinator.index.indexer.search.IndexSearcher;
import com.animatinator.index.normalise.StringNormaliser;

import java.util.*;

public class HashMapFileIndex implements FileIndex {
    private final Map<String, List<FileMatch>> index = new HashMap<>();
    private final StringNormaliser normaliser;

    public HashMapFileIndex(StringNormaliser normaliser) {
        this.normaliser = normaliser;
    }

    public void addMatch(String key, FileMatch match) {
        if (index.containsKey(key)) {
            index.get(key).add(match);
        } else {
            ArrayList<FileMatch> fileMatches = new ArrayList<>();
            fileMatches.add(match);
            index.put(key, fileMatches);
        }
    }

    @Override
    public IndexSearcher getSearcher() {
        return query -> {
            String normalisedString = normaliser.normaliseString(query);
            if (index.containsKey(normalisedString)) {
                return index.get(normalisedString);
            }
            return new ArrayList<>();
        };
    }
}
