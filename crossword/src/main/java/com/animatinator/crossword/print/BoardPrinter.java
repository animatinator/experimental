package com.animatinator.crossword.print;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.board.BoardLayout;
import com.animatinator.crossword.util.BoardPosition;

import java.util.Optional;

public class BoardPrinter {

    private final StringOutput output;

    public BoardPrinter(StringOutput output) {
        this.output = output;
    }

    public void printBoard(Board board) {
        String boardAsString = computeStringRepresentation(board);
        output.outputString(boardAsString);
    }

    private String computeStringRepresentation(Board board) {
        BoardLayout layout = board.getLayout();
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < layout.getHeight(); y++) {
            for (int x = 0; x < layout.getWidth(); x++) {
                Optional<String> tileStringOptional = layout.getAt(new BoardPosition(x, y));
                String tileString = tileStringOptional.orElse(BoardLayout.EMPTY_SPACE);
                builder.append(tileString);
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}