package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

class Board {
    @Nullable
    private String[][] cachedLayout;
    private List<LaidWord> laidWords = new ArrayList<>();

    Board() {}

    void addWord(String word, BoardPosition position, Direction direction) {
        laidWords.add(new LaidWord(word, position, direction));
    }

    String[][] getLayout() {
        if (cachedLayout != null) {
            return  cachedLayout;
        }
        return recomputeLayoutAndCache();
    }

    Boundaries getBoundaries() {
        int left = 0, right = 0, top = 0, bottom = 0;

        for (LaidWord word : laidWords) {
            left = min(left, word.getTopLeft().x());
            right = max(right, word.getBottomRight().x());
            top = min(top, word.getTopLeft().y());
            bottom = max(bottom, word.getBottomRight().y());
        }

        return new Boundaries(new BoardPosition(left, top), new BoardPosition(right, bottom));
    }

    private String[][] recomputeLayoutAndCache() {
        String[][] newLayout = recomputeLayout();
        cachedLayout = newLayout;
        return newLayout;
    }

    private String[][] recomputeLayout() {
        Boundaries boundaries = getBoundaries();
        String[][] layout = createEmptyLayout(boundaries.getWidth(), boundaries.getHeight());

        for (LaidWord word : laidWords) {
            addWordToLayout(word, layout);
        }

        return layout;
    }

    private void addWordToLayout(LaidWord word, String[][] layout) {
        List<String> characters = word.getCharacters();
        BoardPosition startPos = word.getTopLeft();
        int xDirection = (word.getDirection() == Direction.HORIZONTAL) ? 1 : 0;
        int yDirection = (word.getDirection() == Direction.VERTICAL) ? 1 : 0;

        for (int i = 0; i < word.getLength(); i++) {
            layout[startPos.y() + yDirection * i][startPos.x() + xDirection * i] = characters.get(i);
        }
    }

    private String[][] createEmptyLayout(int width, int height) {
        String[][] layout = new String[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                layout[y][x] = ".";
            }
        }

        return layout;
    }
}
