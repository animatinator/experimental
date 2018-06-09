package com.animatinator.index.format;

import com.animatinator.index.indexer.match.FileMatch;
import com.animatinator.index.indexer.match.FileMatchImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SimpleMatchFormatterTest {
    private final SimpleMatchFormatter formatter = new SimpleMatchFormatter();

    @Test
    public void formatSimpleMatch() {
        FileMatch match = new FileMatchImpl("Test.txt", "This is a test.", 1);
        String formatted = formatter.formatMatch(match);
        assertEquals("File: Test.txt\n\tMatches: 1\n\tContent:\n\t\tThis is a test.", formatted);
    }
}
