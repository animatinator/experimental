package com.animatinator.crossword.dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String rawDictionary;
        try {
            rawDictionary = new String(Files.readAllBytes(Paths.get("data\\basicdict.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(rawDictionary);
    }
}
