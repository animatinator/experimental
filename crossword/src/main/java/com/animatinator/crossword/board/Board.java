package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Board {
    @Nullable
    private BoardLayout cachedLayout;
    private List<LaidWord> laidWords = new ArrayList<>();

    public Board() {}

    public void addWord(String word, BoardPosition position, Direction direction) {
        laidWords.add(new LaidWord(word, position, direction));
    }

    public BoardLayout getLayout() {
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

    private BoardLayout recomputeLayoutAndCache() {
        BoardLayout newLayout = recomputeLayout();
        cachedLayout = newLayout;
        return newLayout;
    }

    private BoardLayout recomputeLayout() {
        Boundaries boundaries = getBoundaries();
        BoardLayout layout = new BoardLayout(boundaries.getWidth(), boundaries.getHeight());

        for (LaidWord word : laidWords) {
            addWordToLayout(word, layout);
        }

        return layout;
    }

    private void addWordToLayout(LaidWord word, BoardLayout layout) {
        List<String> characters = word.getCharacters();
        BoardPosition startPos = word.getTopLeft();
        int xDirection = (word.getDirection() == Direction.HORIZONTAL) ? 1 : 0;
        int yDirection = (word.getDirection() == Direction.VERTICAL) ? 1 : 0;

        for (int i = 0; i < word.getLength(); i++) {
            layout.setTile(
                    new BoardPosition(startPos.x() + xDirection * i, startPos.y() + yDirection * i),
                    characters.get(i));
        }
    }
}
