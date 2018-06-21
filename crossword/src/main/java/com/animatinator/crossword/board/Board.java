package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return new String[0][];
    }
}
