package com.animatinator.crossword.board;

import com.animatinator.crossword.board.words.LaidWord;
import com.animatinator.crossword.board.words.WordIntersections;
import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Board {
    private List<LaidWord> laidWords = new ArrayList<>();
    private final WordIntersections intersectionDetector = new WordIntersections();

    public Board() {}

    public void addWord(String word, BoardPosition position, Direction direction) {
        LaidWord wordToLay = new LaidWord(word, position, direction);
        if (canWordBeAdded(wordToLay)) {
            laidWords.add(wordToLay);
        } else {
            throw new IllegalArgumentException("Can't add word here! It intersects incorrectly with an existing word.");
        }
    }

    // TODO: This is a sketch; TDD to completion.
    // TODO: Don't attach a word next to a parallel word (or indeed on top of it).
    public List<LaidWord> getPossibleAttachmentPointsForWord(String wordToAdd) {
        List<LaidWord> attachmentPoints = new ArrayList<>();
        HashSet<String> lettersInNewWord = new HashSet<>(Arrays.asList(wordToAdd.split("")));
        BoardLayout currentBoardLayout = getLayout();

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

                    BoardPosition newAttachmentPosition = new BoardPosition(xPos, yPos);
                    LaidWord possibleNewLaidWord = new LaidWord(wordToAdd, newAttachmentPosition, direction);

                    if (canWordBeAdded(possibleNewLaidWord)) {
                        if (!currentBoardLayout.isAdjacentToExistingIntersection(newAttachmentPosition)) {
                            attachmentPoints.add(possibleNewLaidWord);
                        }
                    }
                }
            }
        }

        return attachmentPoints;
    }

    public BoardLayout getLayout() {
        return recomputeLayout();
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

    private boolean canWordBeAdded(LaidWord wordToLay) {
        return laidWords.stream().noneMatch(
                laidWord -> intersectionDetector.wordsIntersectIllegally(wordToLay, laidWord));
    }
}
