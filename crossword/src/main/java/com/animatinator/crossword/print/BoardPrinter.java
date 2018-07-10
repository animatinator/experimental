package com.animatinator.crossword.print;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.board.BoardLayout;
import com.animatinator.crossword.util.BoardOffset;
import com.animatinator.crossword.util.BoardPosition;

import java.util.Optional;

public class BoardPrinter {

    private final StringOutput output;
    private final BoardToString boardToString;

    public BoardPrinter(StringOutput output) {
        this.output = output;
        boardToString = new BoardToHumanReadableString();
    }

    public void printBoard(Board board) {
        String boardAsString = boardToString.getStringRepresentation(board);
        output.outputString(boardAsString);
    }
}
