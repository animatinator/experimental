package com.animatinator.crossword.generate;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.board.words.LaidWord;
import com.animatinator.crossword.evaluate.BoardEvaluator;
import com.animatinator.crossword.print.BoardPrinter;
import com.animatinator.crossword.print.SystemOutPrinter;
import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BoardGenerator {
    private final BoardEvaluator boardEvaluator;

    public BoardGenerator(BoardEvaluator evaluator) {
        boardEvaluator = evaluator;
    }

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
            // TODO: This is temporary debug logging; clean up or remove.
            double rating = boardEvaluator.evaluateBoard(board);
            System.out.println("Board with rating: "+rating);
            System.out.println("Word count: "+board.getLaidWords().size());
            new BoardPrinter(new SystemOutPrinter()).printBoard(board);
            System.out.println();
        }

        return board;
    }

    private void sortByLengthDescending(List<String> possibleWords) {
        possibleWords.sort((String s1, String s2) -> s2.length() - s1.length());
    }

    private void sortByQuality(List<Board> boards) {
        boards.sort(Comparator.comparingDouble(boardEvaluator::evaluateBoard));
    }

}
