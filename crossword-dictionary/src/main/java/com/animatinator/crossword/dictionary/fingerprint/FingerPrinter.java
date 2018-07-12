package com.animatinator.crossword.dictionary.fingerprint;

import java.util.Arrays;

class FingerPrinter {
    static WordFingerPrint getFingerprint(String word) {
        String[] characters = word.split("");
        Arrays.sort(characters);
        return new WordFingerPrint(characters);
    }
}
