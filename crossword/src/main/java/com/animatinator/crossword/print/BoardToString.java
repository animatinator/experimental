package com.animatinator.crossword.print;

import com.animatinator.crossword.board.Board;

/**
 * Converts a {@link Board} into a string representation.
 */
public interface BoardToString {
    String getStringRepresentation(Board board);
}
