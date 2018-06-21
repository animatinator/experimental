package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;

import java.util.Arrays;
import java.util.List;

class LaidWord {
    private final String word;
    private final BoardPosition position;
    private final Direction direction;

    LaidWord(String word, BoardPosition position, Direction direction) {
        this.word = word;
        this.position = position;
        this.direction = direction;
    }

    String getWord() {
        return word;
    }

    int getLength() {
        return word.length();
    }

    BoardPosition getTopLeft() {
        return position;
    }

    BoardPosition getBottomRight() {
        int xOffset = (direction == Direction.HORIZONTAL) ? (getLength() - 1) : 0;
        int yOffset = (direction == Direction.VERTICAL) ? (getLength() - 1) : 0;

        return getTopLeft().withXOffset(xOffset).withYOffset(yOffset);
    }

    Direction getDirection() {
        return direction;
    }

    List<String> getCharacters() {
        return Arrays.asList(word.split(""));
    }
}
