package com.animatinator.practice.trie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class TrieTest {
    @Test
    public void nothingInEmptyTrie() {
        Trie empty = new Trie();

        assertFalse(empty.contains("test"));
        assertFalse(empty.contains("hello"));
        assertFalse(empty.contains("world"));
    }

    @Test
    public void cannotAddEmptyString() {
        Trie trie = new Trie();

        try {
            trie.insert("");
        } catch (AssertionError expected) {
            return;
        }

        fail("Should have thrown an exception.");
    }

    @Test
    public void cannotTestPresenceOfEmptyString() {
        Trie trie = new Trie();

        try {
            trie.contains("");
        } catch (AssertionError expected) {
            return;
        }

        fail("Should have thrown an exception.");
    }

    @Test
    public void addToTrie_nowContained() {
        Trie trie = new Trie();
        trie.insert("hello");

        assertTrue(trie.contains("hello"));
    }

    @Test
    public void addToTrie_differentBranches() {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("world");

        assertTrue(trie.contains("hello"));
        assertTrue(trie.contains("world"));
    }

    @Test
    public void addToTrie_sameBranch() {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("hellooo");

        assertTrue(trie.contains("hello"));
        assertTrue(trie.contains("hellooo"));
    }

    @Test
    public void subsetOfWordNotContained() {
        Trie trie = new Trie();
        trie.insert("antidisestablishmentarianism");

        assertFalse(trie.contains("antid"));
    }

    @Test
    public void addSubsetOfWordLater() {
        Trie trie = new Trie();
        trie.insert("wordy");
        assertFalse(trie.contains("word"));

        trie.insert("word");

        assertTrue(trie.contains("word"));
    }
}