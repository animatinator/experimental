package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;

import java.util.Arrays;
import java.util.Objects;

public class BoardLayout {
    static final String EMPTY_SPACE = ".";

    private final int width;
    private final int height;
    private String[][] layout;

    BoardLayout(int width, int height) {
        this.width = width;
        this.height = height;
        layout = createEmptyLayout(width, height);
    }

    private String[][] createEmptyLayout(int width, int height) {
        String[][] emptyLayout = new String[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                emptyLayout[y][x] = ".";
            }
        }

        return emptyLayout;
    }

    void setTile(BoardPosition position, String value) {
        layout[position.y()][position.x()] = value;
    }

    public String getAt(BoardPosition position) {
        return layout[position.y()][position.x()];
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    void copyLayoutFromStringArray(String[][] stringArray) {
        layout = stringArray;
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
}
