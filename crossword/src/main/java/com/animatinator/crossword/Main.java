package com.animatinator.crossword;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.evaluate.SimpleBoardEvaluator;
import com.animatinator.crossword.generate.BoardGenerationFlags;
import com.animatinator.crossword.generate.BoardGenerator;
import com.animatinator.crossword.print.BoardPrinter;
import com.animatinator.crossword.print.SystemOutPrinter;

import java.util.Arrays;

public class Main {
    private static final String[] words = new String[]{
            "ads", "cue", "sad", "sea", "sue", "use", "aces", "case", "cues", "dues", "used", "cause", "sauce",
            "caused"};

    public static void main(String[] args) {
        BoardGenerator generator = new BoardGenerator(new SimpleBoardEvaluator(), new BoardGenerationFlags());
        Board board = generator.generateBoard(Arrays.asList(words));
        BoardPrinter printer = new BoardPrinter(new SystemOutPrinter());
        printer.printBoard(board);
    }
}
