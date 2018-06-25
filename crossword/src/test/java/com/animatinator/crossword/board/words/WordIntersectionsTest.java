package com.animatinator.crossword.board.words;

import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class WordIntersectionsTest {
    private static final String TEST = "Test";
    private static final String WORD = "Word";
    private final WordIntersections wordIntersections = new WordIntersections();

    @Test
    public void noIntersection_horizontal() {
        LaidWord first = createWord(TEST, 0, 0, Direction.HORIZONTAL);
        LaidWord second = createWord(WORD, 0, 1, Direction.HORIZONTAL);
        assertFalse(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void noIntersection_vertical() {
        LaidWord first = createWord(TEST, 0, 0, Direction.VERTICAL);
        LaidWord second = createWord(WORD, 1, 0, Direction.VERTICAL);
        assertFalse(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void intersection_horizontal() {
        LaidWord first = createWord(TEST, 0, 0, Direction.HORIZONTAL);
        LaidWord second = createWord(WORD, 2, 0, Direction.HORIZONTAL);
        assertTrue(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void intersection_vertical() {
        LaidWord first = createWord(TEST, 0, 0, Direction.VERTICAL);
        LaidWord second = createWord(WORD, 0, 2, Direction.VERTICAL);
        assertTrue(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void notQuiteIntersection_horizontal() {
        LaidWord first = createWord(TEST, 0, 0, Direction.HORIZONTAL);
        LaidWord second = createWord(WORD, 4, 0, Direction.HORIZONTAL);
        assertFalse(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void onlyJustIntersecting_horizontal() {
        LaidWord first = createWord(TEST, 0, 0, Direction.HORIZONTAL);
        LaidWord second = createWord(WORD, 3, 0, Direction.HORIZONTAL);
        assertTrue(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void notQuiteIntersection_vertical(){
        LaidWord first = createWord(TEST, 0, 0, Direction.VERTICAL);
        LaidWord second = createWord(WORD, 0, 4, Direction.VERTICAL);
        assertFalse(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void onlyJustIntersecting_vertical() {
        LaidWord first = createWord(TEST, 0, 0, Direction.VERTICAL);
        LaidWord second = createWord(WORD, 0, 3, Direction.VERTICAL);
        assertTrue(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void noCrossingIntersection() {
        LaidWord first = createWord(TEST, 0, 2, Direction.HORIZONTAL);
        LaidWord second = createWord(WORD, 3, 0, Direction.VERTICAL);
        assertFalse(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void crossingIntersection() {
        LaidWord first = createWord(TEST, 0, 2, Direction.HORIZONTAL);
        LaidWord second = createWord(WORD, 2, 0, Direction.VERTICAL);
        assertTrue(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void closeCrossingIntersection_topLeft() {
        LaidWord first = createWord(TEST, 0, 0, Direction.HORIZONTAL);
        LaidWord second = createWord(WORD, 0, 0, Direction.VERTICAL);
        assertTrue(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void closeCrossingIntersection_bottomRight() {
        LaidWord first = createWord(TEST, 3, 0, Direction.VERTICAL);
        LaidWord second = createWord(WORD, 0, 3, Direction.HORIZONTAL);
        assertTrue(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void validIntersectionBecauseLettersMatch() {
        LaidWord first = createWord(TEST, 0, 0, Direction.HORIZONTAL);
        LaidWord second = createWord(TEST, 0, 0, Direction.VERTICAL);
        assertFalse(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void anotherValidIntersectionBecauseLettersMatch() {
        LaidWord first = createWord(WORD, 0, 1, Direction.HORIZONTAL);
        LaidWord second = createWord(WORD, 1, 0, Direction.VERTICAL);
        assertFalse(wordIntersections.wordsIntersectIllegally(first, second));
    }

    @Test
    public void sameAxisOverlapNotAllowed() {
        LaidWord first = createWord(TEST, 0, 0, Direction.HORIZONTAL);
        LaidWord second = createWord(TEST, 0, 0, Direction.HORIZONTAL);
        assertTrue(wordIntersections.wordsIntersectIllegally(first, second));
    }

    // TODO using this to debug BoardTest.getAttachments_validUnintendedOverlap
    @Test
    public void nearPerpendicularIntersection() {
        LaidWord first = createWord("patter", 1, 2, Direction.HORIZONTAL);
        LaidWord second = createWord("test", 1, 3, Direction.VERTICAL);
        LaidWord third = createWord("sat", 1, 5, Direction.HORIZONTAL);
        assertFalse(wordIntersections.wordsIntersectIllegally(first, second));
        assertFalse(wordIntersections.wordsIntersectIllegally(second, third));
    }

    private LaidWord createWord(String word, int x, int y, Direction direction) {
        return new LaidWord(word, new BoardPosition(x, y), direction);
    }
}