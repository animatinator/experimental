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
  private BoardLayout simpleBoardLayout;

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

    simpleBoardLayout = new BoardLayout(5, 4);
    simpleBoardLayout.copyLayourFromStringArray(new String[][] {
            {"t", "e", "s", "t", "."},
            {".", ".", ".", "i", "."},
            {"w", "o", "r", "d", "s"},
            {".", ".", ".", "y", "."},});
  }

  @Test
  public void construction() {
      Board board = new Board();
  }

  @Test
  public void emptyLayout() {
    Board board = new Board();
    BoardLayout layout = board.getLayout();
    assertEquals(1, layout.getWidth());
    assertEquals(1, layout.getHeight());
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

  @Test
  public void getLayout() {
    assertEquals(simpleBoardLayout, simpleBoard.getLayout());
  }
}