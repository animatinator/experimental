package com.animatinator.crossword.dictionary.match;

import com.animatinator.crossword.dictionary.fingerprint.WordFingerPrint;

class WordMatcher {
    boolean firstWordCanFormSecond(WordFingerPrint first, WordFingerPrint second) {
        String[] firstCharacters = first.getCharacters();
        String[] secondCharacters = second.getCharacters();
        int firstIndex = 0, secondIndex = 0;

        while (firstIndex < firstCharacters.length && secondIndex < secondCharacters.length) {
            String firstChar = firstCharacters[firstIndex];
            String secondChar = secondCharacters[secondIndex];

            if (firstChar.equalsIgnoreCase(secondChar)) {
                firstIndex++;
                secondIndex++;
            } else if (firstChar.compareToIgnoreCase(secondChar) < 0) {
                firstIndex++;
            } else {
                return false;
            }
        }

        if (secondIndex < secondCharacters.length) {
            return false;
        }

        return true;
    }
}
