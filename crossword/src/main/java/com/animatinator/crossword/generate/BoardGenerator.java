package com.animatinator.crossword.generate;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.board.BoardLayout;
import com.animatinator.crossword.board.words.LaidWord;
import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BoardGenerator {
    // TODO: This is a very rough sketch, with many holes and errors.
    public Board generateBoard(List<String> possibleWords) {
        sortByLengthDescending(possibleWords);
        Queue<String> wordQueue= new LinkedBlockingQueue<>(possibleWords);

        Board board = new Board();
        String firstWord = wordQueue.remove();
        board.addWord(firstWord, new BoardPosition(0, 0), Direction.HORIZONTAL);

        for (String word : wordQueue) {
            List<LaidWord> optionsForWord = board.getPossibleAttachmentPointsForWord(word);
            if (optionsForWord.isEmpty()) continue;

            List<Board> possibleBoards = new ArrayList<>();

            // Evaluate all possible next steps for the board.
            for (LaidWord option : optionsForWord) {
                Board tempBoard = new Board(board);
                tempBoard.addWord(option.getWord(), option.getTopLeft(), option.getDirection());
                possibleBoards.add(tempBoard);
            }

            // Pick the 'best' board.
            sortByQuality(possibleBoards);
            board = possibleBoards.get(0);
        }

        return board;
    }

    private void sortByLengthDescending(List<String> possibleWords) {
        possibleWords.sort((String s1, String s2) -> s2.length() - s1.length());
    }

    private void sortByQuality(List<Board> boards) {
        boards.sort(Comparator.comparingDouble(this::evaluateBoard));
    }

    private double evaluateBoard(Board board) {
        BoardLayout layout = board.getLayout();
        double sizeRatio = layout.getWidth() / layout.getHeight();
        if (sizeRatio > 1.0) sizeRatio = (1.0d / sizeRatio);
        return sizeRatio;
    }
}
