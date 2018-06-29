package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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

}