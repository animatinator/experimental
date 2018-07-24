package com.animatinator.crossword;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.evaluate.SimpleBoardEvaluator;
import com.animatinator.crossword.generate.BoardGenerationFlagConstant;
import com.animatinator.crossword.generate.BoardGenerationFlags;
import com.animatinator.crossword.generate.BoardGenerator;
import com.animatinator.crossword.print.BoardPrinter;
import com.animatinator.crossword.print.BoardToDebugOutput;
import com.animatinator.crossword.print.SystemOutPrinter;

import java.util.Arrays;

public class Main {
    // A testing set of words.
    private static final String[] words = new String[]{
            "ads", "cue", "sad", "sea", "sue", "use", "aces", "case", "cues", "dues", "used", "cause", "sauce",
            "caused"};
    // A set of words generated by https://github.com/animatinator/experimental/tree/master/crossword-dictionary.
    private static final String[] generatedWords = new String[]{
            "true", "elect", "etc", "rule", "cute", "cut", "let", "rule", "lecture", "clue"};

    public static void main(String[] args) {
        BoardGenerationFlags flags = setUpFlags();
        BoardGenerator generator = new BoardGenerator(new SimpleBoardEvaluator(), flags);
        Board board = generator.generateBoard(Arrays.asList(generatedWords));
        BoardPrinter printer = new BoardPrinter(new SystemOutPrinter(), new BoardToDebugOutput(true));
        printer.printBoard(board);
    }

    private static BoardGenerationFlags setUpFlags() {
        BoardGenerationFlags flags = new BoardGenerationFlags();

        flags.setFlag(BoardGenerationFlagConstant.PICK_RANDOMLY_FROM_BEST_FEW_WORD_PLACEMENTS, true);
        flags.setFlag(BoardGenerationFlagConstant.GENERATE_SEVERAL_BOARDS, true);

        return flags;
    }
}
