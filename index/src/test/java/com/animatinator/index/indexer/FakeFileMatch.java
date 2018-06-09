package com.animatinator.index.indexer;

import com.animatinator.index.indexer.match.FileMatch;

public class FakeFileMatch implements FileMatch {
    private final String name;

    @SuppressWarnings("SameParameterValue")
    FakeFileMatch(String name) {
        this.name = name;
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public String getFileContents() {
        return name;
    }

    @Override
    public int getNumMatches() {
        return 1;
    }
}
