package com.animatinator.index.format;

import com.animatinator.index.indexer.match.FileMatch;

public class SimpleMatchFormatter implements MatchFormatter {
    private static final String FORMAT_STRING = "File: %s\n\tMatches: %d\n\tContent:\n\t\t%s";

    @Override
    public String formatMatch(FileMatch match) {
        return String.format(FORMAT_STRING, match.getFileName(), match.getNumMatches(), match.getFileContents());
    }
}
