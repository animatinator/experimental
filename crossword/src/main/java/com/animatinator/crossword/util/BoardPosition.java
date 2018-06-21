package com.animatinator.crossword.util;

import java.util.Objects;

public class BoardPosition {
    private final int x;
    private final int y;

    public BoardPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return  x;
    }

    public int y() {
        return y;
    }

    public BoardPosition withXOffset(int offset) {
        return new BoardPosition(x + offset, y);
    }

    public BoardPosition withYOffset(int offset) {
        return new BoardPosition(x, y + offset);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof BoardPosition)) return false;

        BoardPosition otherPosition = (BoardPosition) other;
        return otherPosition.x == x && otherPosition.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
