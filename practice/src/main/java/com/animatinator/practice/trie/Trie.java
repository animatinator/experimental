package com.animatinator.practice.trie;

import java.util.HashMap;
import java.util.Map;

class Trie {

    private final Map<String, TrieNode> nodes = new HashMap<>();

    void insert(String newValue) {
        assert(newValue.length() > 0);

        TrieNode firstNode = getOrAddNodeForValue(nodes, newValue.substring(0, 1));
        firstNode.insert(newValue.substring(1));
    }

    boolean contains(String key) {
        assert(key.length() > 0);

        String firstCharacter = key.substring(0, 1);
        return nodes.containsKey(firstCharacter) && nodes.get(firstCharacter).contains(key.substring(1));
    }

    private static TrieNode getOrAddNodeForValue(Map<String, TrieNode> nodes, String newValue) {
        if (nodes.containsKey(newValue)) {
            return nodes.get(newValue);
        }

        TrieNode newNode = new TrieNode(newValue);
        nodes.put(newValue, newNode);
        return newNode;
    }

    private static final class TrieNode {
        final String value;
        final Map<String, TrieNode> children = new HashMap<>();

        // Does this node represent the end of an inserted word?
        boolean isValidWord = false;

        TrieNode(String value) {
            this.value = value;
        }

        void insert(String newValue) {
            assert(newValue.length() > 0);

            TrieNode nodeToAddTo = getOrAddNodeForValue(children, newValue.substring(0, 1));

            if (newValue.length() > 1) {
                nodeToAddTo.insert(newValue.substring(1));
            } else {
                nodeToAddTo.isValidWord = true;
            }
        }

        boolean contains(String key) {
            if (key.length() == 0) {
                return isValidWord;
            }

            String firstCharacter = key.substring(0, 1);

            return children.containsKey(firstCharacter) && children.get(firstCharacter).contains(key.substring(1));
        }
    }
}
