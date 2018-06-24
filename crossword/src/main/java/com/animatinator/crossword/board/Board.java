package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Board {
    @Nullable
    private BoardLayout boardLayout;
    private List<LaidWord> laidWords = new ArrayList<>();

    public Board() {}

    public void addWord(String word, BoardPosition position, Direction direction) {
        laidWords.add(new LaidWord(word, position, direction));
    }

    // TODO: This is a sketch; TDD to completion.
    // TODO: Don't attach a word next to a parallel word (or indeed on top of it).
    public List<WordAttachmentPoint> getPossibleAttachmentPointsForWord(String wordToAdd) {
        List<WordAttachmentPoint> attachmentPoints = new ArrayList<>();

        HashSet<String> lettersInNewWord = new HashSet<>(Arrays.asList(wordToAdd.split("")));

        for (LaidWord laidWord : laidWords) {
            List<String> letters = laidWord.getCharacters();

            for (int i = 0; i < laidWord.getLength(); i++) {
                String currentLetter = letters.get(i);
                if (lettersInNewWord.contains(currentLetter)) {
                    int xPos, yPos;

                    if (laidWord.getDirection() == Direction.VERTICAL) {
                        // TODO: This only adds matches for the first instance of a letter in a word.
                        xPos = laidWord.getTopLeft().x() - wordToAdd.indexOf(currentLetter.charAt(0));
                        yPos = laidWord.getTopLeft().y() + i;
                    } else {
                        xPos = laidWord.getTopLeft().x() + i;
                        yPos = laidWord.getTopLeft().y() - wordToAdd.indexOf(currentLetter.charAt(0));
                    }

                    Direction direction =
                            (laidWord.getDirection() == Direction.VERTICAL) ? Direction.HORIZONTAL : Direction.VERTICAL;

                    // TODO: handle other incorrect overlaps.
                    attachmentPoints.add(new WordAttachmentPoint(new BoardPosition(xPos, yPos), direction, currentLetter));
                }
            }
        }

        return attachmentPoints;
    }

    public BoardLayout getLayout() {
        if (boardLayout != null) {
            return boardLayout;
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
        boardLayout = newLayout;
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
            BoardPosition current = new BoardPosition(startPos.x() + xDirection * i, startPos.y() + yDirection * i);
            Optional<String> valueAtCurrent = layout.getAt(current);
            if (valueAtCurrent.isPresent()) {
                // Throw if this word clashes. We should have prevented this elsewhere though.
                if (!valueAtCurrent.get().equals(characters.get(i))) {
                    throw new IllegalArgumentException("Can't add word '"+ word.getWord()+"' because it clashes on character "+characters.get(i));
                }
                layout.markIntersection(current);
            }
            layout.setTile(
                    current,
                    characters.get(i));
        }
    }

    public static class WordAttachmentPoint {
        private final BoardPosition position;
        private final Direction direction;
        // TODO: Remove.
        private String letterForTest;

        WordAttachmentPoint(BoardPosition position, Direction direction, String letterForTest) {
            this.position = position;
            this.direction = direction;
            this.letterForTest = letterForTest;
        }

        public BoardPosition getPosition() {
            return position;
        }

        public Direction getDirection() {
            return direction;
        }
    }
}
