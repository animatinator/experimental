package com.animatinator.crossword.board;

import com.animatinator.crossword.board.words.LaidWord;
import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class BoardLayoutTest {

    @Test
    public void get() {
        BoardLayout layout = new BoardLayout(4, 4);
        layout.copyLayoutFromStringArray(new String[][]{
                ".....".split(""),
                ".....".split(""),
                "...s.".split("")
        });
        Optional<String> boardValue = layout.getAt(new BoardPosition(3, 2));
        assertTrue(boardValue.isPresent());
        assertEquals("s", boardValue.get());
    }

    @Test
    public void get_adjustForOffset() {
        BoardLayout layout = new BoardLayout(4, 4, new BoardPosition(1, 1));
        layout.copyLayoutFromStringArray(new String[][]{
                ".....".split(""),
                ".....".split(""),
                "...s.".split("")
        });
        Optional<String> boardValue = layout.getAt(new BoardPosition(4, 3));
        assertTrue(boardValue.isPresent());
        assertEquals("s", boardValue.get());
    }

    @Test
    public void get_outOfRange() {
        BoardLayout layout = new BoardLayout(1, 1);
        try {
            layout.getAt(new BoardPosition(2, 2));
        } catch (IllegalArgumentException expected) {
            return;
        }

        fail("Should have thrown IllegalArgumentException when getting out of range.");
    }

    @Test
    public void get_outOfRangeWithOffset() {
        BoardLayout layout = new BoardLayout(5, 5, new BoardPosition(-5, -5));
        try {
            layout.getAt(new BoardPosition(0, 0));
        } catch (IllegalArgumentException expected) {
            return;
        }

        fail("Should have thrown IllegalArgumentException when getting out of range.");
    }

    @Test
    public void set_adjustForOffset() {
        BoardLayout layout = new BoardLayout(5, 5, new BoardPosition(-1000, 1000));
        layout.setTile(new BoardPosition(-1000, 1000), "a");
        Optional<String> boardValue = layout.getAt(new BoardPosition(-1000, 1000));
        assertTrue(boardValue.isPresent());
        assertEquals("a", boardValue.get());
    }

    @Test
    public void isAdjacentToExistingWord_triviallyFalse() {
        BoardLayout layout = new BoardLayout(5, 5);
        LaidWord wordToLay = new LaidWord("hello", new BoardPosition(0, 0), Direction.HORIZONTAL);
        assertFalse(layout.isAdjacentToExistingWord(wordToLay));
    }

    /**
     * A word legally overlapping an existing word is valid.
     */
    @Test
    public void isAdjacentToExistingWord_overlapsCorrectly() {
        BoardLayout layout = new BoardLayout(5, 3);
        layout.copyLayoutFromStringArray(new String[][]{
                ".pie.".split(""),
                ".....".split(""),
                ".....".split(""),
        });
        LaidWord wordToLay = new LaidWord("ice", new BoardPosition(2, 0), Direction.VERTICAL);
        assertFalse(layout.isAdjacentToExistingWord(wordToLay));
    }

    /**
     * A word legally overlapping an existing word is valid, even if its letters don't match. Letter matching is not
     * handled by this method.
     */
    @Test
    public void isAdjacentToExistingWord_overlapsCorrectly_badIntersection() {
        BoardLayout layout = new BoardLayout(5, 3);
        layout.copyLayoutFromStringArray(new String[][]{
                ".pie.".split(""),
                ".....".split(""),
                ".....".split(""),
        });
        LaidWord wordToLay = new LaidWord("pie", new BoardPosition(2, 0), Direction.VERTICAL);
        assertFalse(layout.isAdjacentToExistingWord(wordToLay));
    }

    @Test
    public void isAdjacentToExistingWord_triviallyTrue() {
        BoardLayout layout = new BoardLayout(5, 3);
        layout.copyLayoutFromStringArray(new String[][]{
                ".pie.".split(""),
                ".....".split(""),
                ".....".split(""),
        });
        LaidWord wordToLay = new LaidWord("pie", new BoardPosition(1, 1), Direction.HORIZONTAL);
        assertTrue(layout.isAdjacentToExistingWord(wordToLay));
    }

    @Test
    public void isAdjacentToExistingWord_onlyJust() {
        BoardLayout layout = new BoardLayout(6, 3);
        layout.copyLayoutFromStringArray(new String[][]{
                "......".split(""),
                ".pie..".split(""),
                "......".split(""),
        });
        LaidWord wordToLay = new LaidWord("pie", new BoardPosition(3, 0), Direction.HORIZONTAL);
        assertTrue(layout.isAdjacentToExistingWord(wordToLay));
    }

    @Test
    public void isAdjacentToExistingWord_diagonalMiss() {
        BoardLayout layout = new BoardLayout(5, 4);
        layout.copyLayoutFromStringArray(new String[][]{
                ".pie.".split(""),
                ".....".split(""),
                ".....".split(""),
                ".....".split(""),
        });
        LaidWord wordToLay = new LaidWord("pie", new BoardPosition(0, 1), Direction.VERTICAL);
        assertFalse(layout.isAdjacentToExistingWord(wordToLay));
    }

    @Test
    public void isAdjacentToExistingWord_validCorner() {
        BoardLayout layout = new BoardLayout(5, 3);
        layout.copyLayoutFromStringArray(new String[][]{
                ".pie.".split(""),
                ".....".split(""),
                ".....".split(""),
        });
        LaidWord wordToLay = new LaidWord("eel", new BoardPosition(3, 0), Direction.VERTICAL);
        assertFalse(layout.isAdjacentToExistingWord(wordToLay));
    }

    @Test
    public void isAdjacentToExistingWord_validCrossing() {
        BoardLayout layout = new BoardLayout(5, 3);
        layout.copyLayoutFromStringArray(new String[][]{
                ".....".split(""),
                ".pie.".split(""),
                ".....".split(""),
        });
        LaidWord wordToLay = new LaidWord("lie", new BoardPosition(2, 0), Direction.VERTICAL);
        assertFalse(layout.isAdjacentToExistingWord(wordToLay));
    }

    @Test
    public void isAdjacentToExistingWord_accidentalContinuation() {
        BoardLayout layout = new BoardLayout(5, 1);
        layout.copyLayoutFromStringArray(new String[][]{
                "pie..".split("")
        });
        LaidWord wordToLay = new LaidWord("eel", new BoardPosition(2, 0), Direction.HORIZONTAL);
        assertTrue(layout.isAdjacentToExistingWord(wordToLay));
    }

    // TODO suspicion: additions that don't fully fit on the existing layout aren't handled properly. Test specifically.

    // TODO: Use this to debug the generation in https://pastebin.com/HmEB57w0. This is a rough recreation.
    @Test
    public void isAdjacentToExistingWord_horizontalMerge() {
        BoardLayout layout = new BoardLayout(0, 0);
        layout.copyLayoutFromStringArray(new String[][]{
                "..u..".split(""),
                "..s..".split(""),
                ".cede".split(""),
        });
        LaidWord wordToLay = new LaidWord("sis", new BoardPosition(0, 1), Direction.HORIZONTAL);
        assertTrue(layout.isAdjacentToExistingWord(wordToLay));
    }

    // TODO: Use this to debug the generation at https://pastebin.com/eQQKRquc.
    @Test
    public void isAdjacentToExistingWord_verticalMerge() {
        BoardLayout layout = new BoardLayout(6, 7);
        layout.copyLayoutFromStringArray(new String[][]{
                "..s...".split(""),
                "..a...".split(""),
                "caused".split(""),
                "a.c...".split(""),
                "u.e...".split(""),
                "s.....".split(""),
                "e.....".split(""),
        });
        LaidWord wordToLay = new LaidWord("aces", new BoardPosition(3, -1), Direction.VERTICAL);
        assertTrue(layout.isAdjacentToExistingWord(wordToLay));
    }
}