package com.animatinator.index.indexer;

import com.animatinator.index.files.File;
import com.animatinator.index.indexer.FileIndexer;
import com.animatinator.index.indexer.match.FileMatch;
import com.animatinator.index.indexer.search.IndexSearcher;
import com.animatinator.index.normalise.FakeStringNormaliser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FileIndexerTest {
    private static final String TEST_WORD = "test";
    private static final String TEST_FILE_NAME = "Test.txt";
    private static final String FOX_WORD = "fox";
    private static final String FOXES_FILE_NAME = "Foxes.txt";
    private static final String FOX_FILE_NAME = "Fox.txt";
    private FileIndexer fileIndexer;
    private File testFile;
    private File threeFoxes;
    private File oneFox;

    @Before
    public void setUp() {
        fileIndexer = new FileIndexer(new FakeStringNormaliser());
        testFile = new File(TEST_FILE_NAME, "test");
        threeFoxes = new File(FOXES_FILE_NAME, "fox fox fox");
        oneFox = new File(FOX_FILE_NAME, "fox");
    }

    @Test
    public void emptyIndex() {
        IndexSearcher searcher = fileIndexer.getSearcher();
        assertFalse(searcher.findInIndex(TEST_WORD).iterator().hasNext());
    }

    @Test
    public void testFile() {
        fileIndexer.addFileToIndex(testFile);
        IndexSearcher searcher = fileIndexer.getSearcher();

        Iterator<FileMatch> matches = searcher.findInIndex(TEST_WORD).iterator();

        assertTrue(matches.hasNext());
        FileMatch match = matches.next();
        assertEquals(TEST_FILE_NAME, match.getFileName());
        assertEquals(1, match.getNumMatches());
        assertFalse(matches.hasNext());
    }

    @Test
    public void threeFoxes() {
        fileIndexer.addFileToIndex(threeFoxes);
        IndexSearcher searcher = fileIndexer.getSearcher();

        Iterator<FileMatch> matches = searcher.findInIndex(FOX_WORD).iterator();

        assertTrue(matches.hasNext());
        FileMatch match = matches.next();
        assertEquals(FOXES_FILE_NAME, match.getFileName());
        assertEquals(3, match.getNumMatches());
    }

    @Test
    public void twoMatches() {
        fileIndexer.addFilesToIndex(Arrays.asList(threeFoxes, oneFox));
        IndexSearcher searcher = fileIndexer.getSearcher();

        Iterator<FileMatch> matches = searcher.findInIndex(FOX_WORD).iterator();

        assertTrue(matches.hasNext());
        FileMatch match = matches.next();
        assertEquals(FOXES_FILE_NAME, match.getFileName());
        assertEquals(3, match.getNumMatches());

        assertTrue(matches.hasNext());
        match = matches.next();
        assertEquals(FOX_FILE_NAME, match.getFileName());
        assertEquals(1, match.getNumMatches());
    }
}
