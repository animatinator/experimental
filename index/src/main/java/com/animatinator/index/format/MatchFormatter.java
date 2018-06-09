package com.animatinator.index.format;

import com.animatinator.index.indexer.match.FileMatch;

public interface MatchFormatter {
    String formatMatch(FileMatch match);
}
