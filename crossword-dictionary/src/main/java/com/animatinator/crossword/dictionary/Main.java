package com.animatinator.crossword.dictionary;

import com.animatinator.crossword.dictionary.processed.ProcessedDictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        ProcessedDictionary dictionary;
        try {
            dictionary = loadDictionaryFromFile(Paths.get(args[0]));
        } catch (IOException e) {
            System.out.println("Failed to load dictionary! Exception: "+e);
            e.printStackTrace();
        }
    }

    private static ProcessedDictionary loadDictionaryFromFile(Path path) throws IOException {
        String[] words = loadWordsFromFile(path);
        return buildDictionaryFromWords(words);
    }

    private static String[] loadWordsFromFile(Path path) throws IOException {
        String rawDictionary = new String(Files.readAllBytes(path));
        return rawDictionary.split(System.getProperty("line.separator"));
    }

    private static ProcessedDictionary buildDictionaryFromWords(String[] words) {
        ProcessedDictionary dictionary = new ProcessedDictionary();

        for (String word : words) {
            dictionary.addWord(word);
        }

        return dictionary;
    }
}
