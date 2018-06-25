package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class BoardLayout {
    public static final String EMPTY_SPACE = ".";

    private int width;
    private int height;
    private BoardTile[][] layout;

    BoardLayout(int width, int height) {
        this.width = width;
        this.height = height;
        layout = createEmptyLayout(width, height);
    }

    private BoardTile[][] createEmptyLayout(int width, int height) {
        BoardTile[][] emptyLayout = new BoardTile[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                emptyLayout[y][x] = new BoardTile();
            }
        }

        return emptyLayout;
    }

    void setTile(BoardPosition position, String value) {
        valueAt(position).setValue(value);
    }

    void markIntersection(BoardPosition position) {
        valueAt(position).markAsIntersection();
    }

    public Optional<String> getAt(BoardPosition position) {
        if (!isOnBoard(position)) {
            throw new IllegalArgumentException("Requesting a position which isn't on the board!");
        }
        return valueAt(position).getValue();
    }

    boolean isIntersection(BoardPosition position) {
        return isOnBoard(position) && valueAt(position).isIntersection();
    }

    private boolean isOnBoard(BoardPosition position) {
        return position.x() >= 0 && position.x() < width && position.y() >= 0 && position.y() < height;
    }

    private BoardTile valueAt(BoardPosition position) {
        return layout[position.y()][position.x()];
    }

    boolean isAdjacentToExistingIntersection(BoardPosition position) {for (int x = -1; x <= 1; x++) {
        for (int y = -1; y <= 1; y++) {
            if (isIntersection(position.withXOffset(x).withYOffset(y))) {
                return true;
            }
        }
    }

        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // For testing.
    void copyLayoutFromStringArray(String[][] stringArray) {
        if (stringArray.length == 0) {
            throw new IllegalArgumentException("Layout array cannot be empty");
        }
        height = stringArray.length;
        width = stringArray[0].length;

        layout = createLayoutArrayFromStringArray(stringArray);
    }

    private BoardTile[][] createLayoutArrayFromStringArray(String[][] stringArray) {
        int newHeight = stringArray.length;
        int newWidth = stringArray[0].length;

        BoardTile[][] newLayout = createEmptyLayout(newWidth, newHeight);

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                String value = stringArray[y][x];
                if (!value.equals(EMPTY_SPACE)) {
                    newLayout[y][x].setValue(value);
                }
            }
        }

        return newLayout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardLayout that = (BoardLayout) o;
        return width == that.width &&
                height == that.height &&
                Arrays.deepEquals(layout, that.layout);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(width, height);
        result = 31 * result + Arrays.hashCode(layout);
        return result;
    }

    /**
     * Tracks the value of an individual tile. Tracks whether the tile is an intersection between two words so we can
     * more easily check validities of new intersections.
     */
    private static final class BoardTile {
        private boolean isIntersection;
        @Nullable
        private String value;

        void setValue(@Nullable String value) {
            this.value = value;
        }

        void markAsIntersection() {
            isIntersection = true;
        }

        Optional<String> getValue() {
            return Optional.ofNullable(value);
        }

        boolean isIntersection() {
            return isIntersection;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BoardTile boardTile = (BoardTile) o;
            // We ignore whether or not this is an intersection because they may not have been computed for test layouts
            // (see copyLayoutFromStringArray). This is unimportant because intersections are an emergent property of
            // layouts, and the boolean here is just a way of keeping track of them internally.
            return Objects.equals(value, boardTile.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(isIntersection, value);
        }
    }
}
