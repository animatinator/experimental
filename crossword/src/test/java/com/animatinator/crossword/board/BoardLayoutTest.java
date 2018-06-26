package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class BoardLayoutTest {

    @Test
    public void get_outOfRange() {
        BoardLayout layout = new BoardLayout(1, 1, new BoardPosition(0, 0));
        try {
            layout.getAt(new BoardPosition(2, 2));
        } catch (IllegalArgumentException expected) {
            return;
        }

        fail("Should have thrown IllegalArgumentException when getting out of range.");
    }

    @Test
    public void adjacentIntersection() {
        BoardLayout layout = new BoardLayout(10, 10, new BoardPosition(0, 0));
        layout.markIntersection(new BoardPosition(1, 1));

        assertTrue(layout.isAdjacentToExistingIntersection(new BoardPosition(0, 0)));
        assertTrue(layout.isAdjacentToExistingIntersection(new BoardPosition(1, 0)));
        assertTrue(layout.isAdjacentToExistingIntersection(new BoardPosition(2, 0)));
        assertTrue(layout.isAdjacentToExistingIntersection(new BoardPosition(0, 1)));
        assertTrue(layout.isAdjacentToExistingIntersection(new BoardPosition(1, 1)));
        assertTrue(layout.isAdjacentToExistingIntersection(new BoardPosition(2, 1)));
        assertTrue(layout.isAdjacentToExistingIntersection(new BoardPosition(0, 2)));
        assertTrue(layout.isAdjacentToExistingIntersection(new BoardPosition(1, 2)));
        assertTrue(layout.isAdjacentToExistingIntersection(new BoardPosition(2, 2)));
    }

    @Test
    public void notAdjacentToIntersection() {
        BoardLayout layout = new BoardLayout(10, 10, new BoardPosition(0, 0));
        layout.markIntersection(new BoardPosition(1, 1));

        assertFalse(layout.isAdjacentToExistingIntersection(new BoardPosition(3, 2)));
    }

}