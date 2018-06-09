package com.animatinator.index.indexer.search;

import com.animatinator.index.indexer.match.FileMatch;

import java.util.List;

public interface IndexSearcher {
    List<FileMatch> findInIndex(String query);
}
