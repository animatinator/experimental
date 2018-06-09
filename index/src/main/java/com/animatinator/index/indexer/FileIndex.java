package com.animatinator.index.indexer;

import com.animatinator.index.indexer.search.IndexSearcher;

interface FileIndex {
    IndexSearcher getSearcher();
}
