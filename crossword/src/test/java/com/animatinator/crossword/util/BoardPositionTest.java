package com.animatinator.crossword.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BoardPositionTest {
    @Test
    public void addXOffset() {
        BoardPosition position = new BoardPosition(1, 2);
        BoardPosition adjusted = position.withXOffset(3);
        assertEquals(4, adjusted.x());
    }

    @Test
    public void addYOffset() {
        BoardPosition position = new BoardPosition(1, 2);
        BoardPosition adjusted = position.withYOffset(3);
        assertEquals(5, adjusted.y());
    }

    @Test
    public void immutable() {
        BoardPosition position = new BoardPosition(1, 2);
        position.withXOffset(3);
        position.withYOffset(3);
        assertEquals(1, position.x());
        assertEquals(2, position.y());
    }
}