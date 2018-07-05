package com.animatinator.crossword.evaluate;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.board.BoardLayout;

public class SimpleBoardEvaluator implements BoardEvaluator {
    @Override
    public double evaluateBoard(Board board) {
        BoardLayout layout = board.getLayout();
        double aspectRatio = getAspectRatio(layout);
        double longestSide = getLongestSide(layout);
        double numWords = board.getLaidWords().size();
        return numWords / (longestSide * aspectRatio);
    }

    private double getAspectRatio(BoardLayout layout) {
        double sizeRatio = (double)layout.getWidth() / (double)layout.getHeight();
        if (sizeRatio < 1.0) sizeRatio = (1.0d / sizeRatio);
        return sizeRatio;
    }

    private double getLongestSide(BoardLayout layout) {
        return Math.max(layout.getWidth(), layout.getHeight());
    }
}
