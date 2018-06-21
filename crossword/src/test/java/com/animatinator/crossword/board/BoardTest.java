package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BoardTest {
  private Board simpleBoard;

  /**
   * test..
   * ...i..
   * words.
   * ...y..
   */
  @Before
  public void setUpSimpleBoard() {
    simpleBoard = new Board();
    simpleBoard.addWord("test", new BoardPosition(0, 0), Direction.HORIZONTAL);
    simpleBoard.addWord("words", new BoardPosition(0, 2), Direction.HORIZONTAL);
    simpleBoard.addWord("tidy", new BoardPosition(3, 0), Direction.VERTICAL);
  }

  @Test
  public void construction() {
      Board board = new Board();
  }

  @Test
  public void emptyLayout() {
      Board board = new Board();
      String[][] layout = board.getLayout();
      assertEquals(0, layout.length);
  }

  @Test
  public void simpleBoundaries() {
    Board board = new Board();
    board.addWord("test", new BoardPosition(0, 0), Direction.HORIZONTAL);

    Boundaries boundaries = board.getBoundaries();
    assertEquals(new BoardPosition(0, 0), boundaries.getTopLeft());
    assertEquals(new BoardPosition(3, 0), boundaries.getBottomRight());
  }

  @Test
  public void boundariesFromMultipleWords() {
    Boundaries boundaries = simpleBoard.getBoundaries();
    assertEquals(new BoardPosition(0, 0), boundaries.getTopLeft());
    assertEquals(new BoardPosition(4, 3), boundaries.getBottomRight());
  }
}