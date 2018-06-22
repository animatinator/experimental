package com.animatinator.crossword;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.generate.BoardGenerator;
import com.animatinator.crossword.print.BoardPrinter;
import com.animatinator.crossword.print.SystemOutPrinter;
import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String[] words = new String[]{
            "ads", "cue", "sad", "sea", "sue", "use", "aces", "case", "cues", "dues", "used", "cause", "sauce",
            "caused"};

    public static void main(String[] args) {
        BoardGenerator generator = new BoardGenerator();
        Board board = generator.generateBoard(Arrays.asList(words));
        board.addWord("test", new BoardPosition(1, 1), Direction.VERTICAL);
        board.addWord("hello", new BoardPosition(0, 2), Direction.HORIZONTAL);
        BoardPrinter printer = new BoardPrinter(new SystemOutPrinter());
        printer.printBoard(board);
        List<Board.WordAttachmentPoint> matches = board.getPossibleAttachmentPointsForWord("lest");
    }
}
