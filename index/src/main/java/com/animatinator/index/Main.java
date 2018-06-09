package com.animatinator.index;

import com.animatinator.index.files.File;
import com.animatinator.index.format.MatchFormatter;
import com.animatinator.index.format.SimpleMatchFormatter;
import com.animatinator.index.indexer.builder.FileIndexer;
import com.animatinator.index.indexer.match.FileMatch;
import com.animatinator.index.normalise.StringNormaliser;
import com.animatinator.index.normalise.StringNormaliserImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        StringNormaliser stringNormaliser = new StringNormaliserImpl();
        FileIndexer indexer = new FileIndexer(stringNormaliser);

        System.out.println("Loading files...");
        List<File> files = parseArgs(args);

        System.out.println("Indexing...");
        indexer.addFilesToIndex(files);

        System.out.println("Ready.\n\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a word to search for, or 'q' to quit:");
            String currentInput = scanner.next();

            if (currentInput.trim().equals("q")) {
                System.out.println("Goodbye!");
                break;
            }

            System.out.println("Searching for '"+currentInput+"...");
            List<FileMatch> matches = indexer.getSearcher().findInIndex(currentInput);
            System.out.println("Found "+matches.size()+" matches:");

            MatchFormatter formatter = new SimpleMatchFormatter();

            for (FileMatch match : matches) {
                System.out.println(formatter.formatMatch(match));
            }
        }
    }

    private static List<File> parseArgs(String[] args) {
        List<File> files = new ArrayList<>();

        for (String arg : args) {
            try {
                Path path = Paths.get(arg);
                String fileName = path.getFileName().toString();
                String content = new String(Files.readAllBytes(path));
                files.add(new File(fileName, content));
            } catch (IOException e) {
                System.out.println("Failed to load '" + arg + "'. Exception: "+e);
            }
        }

        return files;
    }
}
