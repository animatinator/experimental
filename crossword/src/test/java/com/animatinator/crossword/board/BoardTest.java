package com.animatinator.crossword.board;

import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
    simpleBoardLayout.copyLayoutFromStringArray(new String[][] {
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
  public void wordLaidCorrectly_vertical() {
    Board board = new Board();
    board.addWord("word", new BoardPosition(3, 4), Direction.VERTICAL);
    BoardLayout layout = board.getLayout();
    assertValueAtPositionEquals(layout, 3, 4, "w");
    assertValueAtPositionEquals(layout, 3, 5, "o");
    assertValueAtPositionEquals(layout, 3, 6, "r");
    assertValueAtPositionEquals(layout, 3, 7, "d");
  }

  @Test
  public void wordLaidCorrectly_horizontal() {
    Board board = new Board();
    board.addWord("word", new BoardPosition(3, 4), Direction.HORIZONTAL);
    BoardLayout layout = board.getLayout();
    assertValueAtPositionEquals(layout, 3, 4, "w");
    assertValueAtPositionEquals(layout, 4, 4, "o");
    assertValueAtPositionEquals(layout, 5, 4, "r");
    assertValueAtPositionEquals(layout, 6, 4, "d");
  }

  @Test
  public void intersectionMarkedCorrectly() {
    Board board = new Board();
    board.addWord("patter", new BoardPosition(1, 2), Direction.HORIZONTAL);
    board.addWord("test", new BoardPosition(3, 2), Direction.VERTICAL);
    BoardLayout layout = board.getLayout();
    assertValueAtPositionEquals(layout, 3, 2, "t");
    assertTrue(layout.isIntersection(new BoardPosition(3, 2)));
  }

  @Test
  public void badIntersectionDenied() {
      Board board = new Board();
      board.addWord("patter", new BoardPosition(1, 2), Direction.HORIZONTAL);
      try {
          board.addWord("test", new BoardPosition(2, 2), Direction.VERTICAL);
      } catch (IllegalArgumentException expected) {
          return;
      }
      fail("Shouldn't have been able to add 'test' there");
  }

  @Test
  public void getLayout() {
    assertEquals(simpleBoardLayout, simpleBoard.getLayout());
  }

  private void assertValueAtPositionEquals(BoardLayout layout, int x, int y, String expectedValue) {
    Optional<String> optionalValue = layout.getAt(new BoardPosition(x, y));
    assertTrue(optionalValue.isPresent());
    assertEquals(expectedValue, optionalValue.get());
  }
}