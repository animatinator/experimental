package com.animatinator.index.indexer.match;

public interface FileMatch {
    String getFileName();

    String getFileContents();

    int getNumMatches();
}
