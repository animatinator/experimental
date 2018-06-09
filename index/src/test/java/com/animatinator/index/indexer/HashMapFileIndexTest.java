package com.animatinator.index.indexer;

import com.animatinator.index.indexer.match.FileMatch;
import com.animatinator.index.indexer.search.IndexSearcher;
import com.animatinator.index.normalise.FakeStringNormaliser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class HashMapFileIndexTest {
    private static final String TEST_WORD = "test";

    private HashMapFileIndex hashMapFileIndex;
    private FileMatch fileMatch;

    @Before
    public void setUp() {
        hashMapFileIndex = new HashMapFileIndex(new FakeStringNormaliser());
        fileMatch = new FakeFileMatch("test_file");
    }

    @Test
    public void findsBasicEntry() {
        hashMapFileIndex.addMatch(TEST_WORD, fileMatch);
        Iterable<FileMatch> matches = getMatches(hashMapFileIndex, TEST_WORD);
        assertTrue(matches.iterator().hasNext());
        assertEquals(fileMatch, matches.iterator().next());
    }

    @Test
    public void noMatch() {
        Iterable<FileMatch> matches = getMatches(hashMapFileIndex, TEST_WORD);
        assertFalse(matches.iterator().hasNext());
    }

    @Test
    public void multipleMatches() {
        hashMapFileIndex.addMatch(TEST_WORD, fileMatch);
        hashMapFileIndex.addMatch(TEST_WORD, fileMatch);

        Iterable<FileMatch> matches = getMatches(hashMapFileIndex, TEST_WORD);
        Iterator<FileMatch> matchIterator = matches.iterator();

        assertTrue(matchIterator.hasNext());
        assertEquals(fileMatch, matchIterator.next());
        assertTrue(matchIterator.hasNext());
        assertEquals(fileMatch, matchIterator.next());
    }

    @SuppressWarnings("SameParameterValue")
    private Iterable<FileMatch> getMatches(FileIndex fileIndex, String query) {
        IndexSearcher searcher = fileIndex.getSearcher();
        return searcher.findInIndex(query);
    }
}
