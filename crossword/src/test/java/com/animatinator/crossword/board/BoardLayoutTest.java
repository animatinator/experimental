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
}