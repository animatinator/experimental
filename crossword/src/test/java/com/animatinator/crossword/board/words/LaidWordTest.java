package com.animatinator.crossword.board.words;

import com.animatinator.crossword.board.words.LaidWord;
import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class LaidWordTest {
    @Test
    public void construction() {
        LaidWord word = new LaidWord("test", new BoardPosition(1, 2), Direction.HORIZONTAL);
        assertEquals("test", word.getWord());
        assertEquals(1, word.getTopLeft().x());
        assertEquals(2, word.getTopLeft().y());
        assertEquals(Direction.HORIZONTAL, word.getDirection());
    }

    @Test
    public void bottomRight_horizontalWord() {
        LaidWord word = new LaidWord("test", new BoardPosition(1, 2), Direction.HORIZONTAL);
        BoardPosition bottomRight = word.getBottomRight();
        assertEquals(4, bottomRight.x());
        assertEquals(2, bottomRight.y());
    }

    @Test
    public void bottomRight_verticalWord() {
        LaidWord word = new LaidWord("test", new BoardPosition(1, 2), Direction.VERTICAL);
        BoardPosition bottomRight = word.getBottomRight();
        assertEquals(1, bottomRight.x());
        assertEquals(5, bottomRight.y());
    }
}