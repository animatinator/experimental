package com.animatinator.index.indexer;

import com.animatinator.index.indexer.match.FileMatch;
import com.animatinator.index.indexer.search.IndexSearcher;

interface FileIndex {
    void addMatch(String key, FileMatch match);

    IndexSearcher getSearcher();
}
