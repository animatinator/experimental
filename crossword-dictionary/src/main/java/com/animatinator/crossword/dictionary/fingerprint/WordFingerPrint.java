package com.animatinator.crossword.dictionary.fingerprint;

import java.util.Arrays;

public class WordFingerPrint {
    private String[] characters;

    WordFingerPrint(String[] characters) {
        this.characters = characters;
    }

    @Override public String toString() {
        return String.join("", characters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordFingerPrint that = (WordFingerPrint) o;
        return Arrays.equals(characters, that.characters);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(characters);
    }
}
