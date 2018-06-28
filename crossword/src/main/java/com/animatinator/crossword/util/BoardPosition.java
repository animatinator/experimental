package com.animatinator.crossword.util;

import java.util.Objects;

public class BoardPosition {
    private final int x;
    private final int y;

    public BoardPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public BoardPosition(BoardPosition other) {
        this.x = other.x;
        this.y = other.y;
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

    public BoardPosition withOffset(BoardPosition offset) {
        return new BoardPosition(x + offset.x, y + offset.y);
    }

    public BoardPosition negative() {
        return new BoardPosition(-x, -y);
    }

    public BoardPosition multiply(int factor) {
        return new BoardPosition(x * factor, y * factor);
    }

    @Override
    public String toString() {
        return String.format("BoardPosition(%d, %d)", x, y);
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
