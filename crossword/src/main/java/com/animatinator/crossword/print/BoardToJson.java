package com.animatinator.crossword.print;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.board.BoardLayout;
import com.animatinator.crossword.util.BoardOffset;
import com.animatinator.crossword.util.BoardPosition;

import java.util.Optional;

public class BoardToJson implements BoardToString {
    @Override
    public String getStringRepresentation(Board board) {
        BoardLayout layout = board.getLayout();
        StringBuilder builder = new StringBuilder("[");

        BoardPosition topLeft = layout.getTopLeft();

        for (int y = 0; y < layout.getHeight(); y++) {
            builder.append("[");
            for (int x = 0; x < layout.getWidth(); x++) {
                // TODO: This is a bit of a hack: we're offsetting the coordinates we pass in to account for the inverse
                // offset the layout does internally.
                Optional<String> tileStringOptional =
                        layout.getAt(new BoardPosition(x, y).withOffset(new BoardOffset(topLeft)));
                if (tileStringOptional.isPresent()) {
                    builder.append(String.format("\"%s\"", tileStringOptional.get()));
                } else {
                    builder.append("null");
                }
                if (x < layout.getWidth() - 1) {
                    builder.append(", ");
                }
            }
            builder.append("]");
            if (y < layout.getHeight() - 1) {
                builder.append(", ");
            }
        }

        builder.append("]");

        return builder.toString();
    }
}
