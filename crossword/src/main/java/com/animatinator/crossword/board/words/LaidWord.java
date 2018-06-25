package com.animatinator.crossword.board.words;

import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;

import java.util.Arrays;
import java.util.List;

public class LaidWord {
    private final String word;
    private final BoardPosition position;
    private final Direction direction;

    public LaidWord(String word, BoardPosition position, Direction direction) {
        this.word = word;
        this.position = position;
        this.direction = direction;
    }

    public String getWord() {
        return word;
    }

    public int getLength() {
        return word.length();
    }

    public BoardPosition getTopLeft() {
        return position;
    }

    public BoardPosition getBottomRight() {
        int xOffset = (direction == Direction.HORIZONTAL) ? (getLength() - 1) : 0;
        int yOffset = (direction == Direction.VERTICAL) ? (getLength() - 1) : 0;

        return getTopLeft().withXOffset(xOffset).withYOffset(yOffset);
    }

    public Direction getDirection() {
        return direction;
    }

    public List<String> getCharacters() {
        return Arrays.asList(word.split(""));
    }
}
