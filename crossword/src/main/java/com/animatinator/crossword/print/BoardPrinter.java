package com.animatinator.crossword.print;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.board.BoardLayout;
import com.animatinator.crossword.util.BoardOffset;
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

        BoardPosition topLeft = layout.getTopLeft();
        for (int y = 0; y < layout.getHeight(); y++) {
            for (int x = 0; x < layout.getWidth(); x++) {
                // TODO: This is a bit of a hack: we're offsetting the coordinates we pass in to account for the inverse
                // offset the layout does internally.
                Optional<String> tileStringOptional =
                        layout.getAt(new BoardPosition(x, y).withOffset(new BoardOffset(topLeft)));
                String tileString = tileStringOptional.orElse(BoardLayout.EMPTY_SPACE);
                builder.append(tileString);
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
