package com.animatinator.crossword.dictionary.fingerprint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FingerPrinter {
    public static WordFingerPrint getFingerprint(String word) {
        String[] characters = word.split("");
        Arrays.sort(characters);
        return new WordFingerPrint(removeDuplicates(characters));
    }

    private static String[] removeDuplicates(String[] sortedCharacters) {
        List<String> uniqueCharacters = new ArrayList<>();

        String last = "";
        for (String character : sortedCharacters) {
            if (!character.equals(last)) {
                last = character;
                uniqueCharacters.add(character);
            }
        }

        String[] result = new String[uniqueCharacters.size()];
        uniqueCharacters.toArray(result);
        return result;
    }
}
