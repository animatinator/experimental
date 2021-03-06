package com.animatinator.crossword.util;

public class BoardPosition extends Vector2d {

    public BoardPosition(int x, int y) {
        super(x, y);
    }

    public BoardPosition(BoardPosition other) {
        super(other);
    }

    public BoardPosition withXOffset(int offset) {
        return new BoardPosition(x() + offset, y());
    }

    public BoardPosition withYOffset(int offset) {
        return new BoardPosition(x(), y() + offset);
    }

    public BoardPosition withOffset(BoardOffset offset) {
        return new BoardPosition(x() + offset.x(), y() + offset.y());
    }

    @Override
    public String toString() {
        return String.format("BoardPosition(%d, %d)", x, y);
    }

}
