package com.animatinator.crossword.generate;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.board.words.LaidWord;
import com.animatinator.crossword.evaluate.BoardEvaluator;
import com.animatinator.crossword.util.BoardPosition;
import com.animatinator.crossword.util.Direction;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class BoardGenerator {
    private static final Random randomGenerator = new Random();

    private final BoardEvaluator boardEvaluator;
    private final BoardGenerationFlags flags;

    public BoardGenerator(BoardEvaluator evaluator, BoardGenerationFlags flags) {
        boardEvaluator = evaluator;
        this.flags = flags;
    }

    // TODO: This is a very rough sketch, with many holes and errors.
    public Board generateBoard(List<String> possibleWords) {
        sortByLengthDescending(possibleWords);
        Queue<String> wordQueue= new LinkedBlockingQueue<>(possibleWords);

        Board board = new Board();
        String firstWord = wordQueue.remove();
        board.addWord(firstWord, new BoardPosition(0, 0), generateInitialOrientation());

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

            if (flags.getFlag(BoardGenerationFlagConstant.PICK_RANDOMLY_FROM_BEST_FEW_WORD_PLACEMENTS)) {
                // Pick randomly from the top 50% of possible boards.
                int limitingIndex = (int) ((double) possibleBoards.size() * 0.5d);
                // Make sure the limiting index is at least one.
                limitingIndex = Math.max(limitingIndex, 1);
                int indexToPick = randomGenerator.nextInt(limitingIndex);
                board = possibleBoards.get(indexToPick);
            } else {
                board = possibleBoards.get(0);
            }
        }

        return board;
    }

    private Direction generateInitialOrientation() {
        if (flags.getFlag(BoardGenerationFlagConstant.RANDOM_INITIAL_ORIENTATION)) {
            double random = randomGenerator.nextFloat();
            return random > 0.5 ? Direction.HORIZONTAL : Direction.VERTICAL;
        } else {
            return Direction.HORIZONTAL;
        }
    }

    private void sortByLengthDescending(List<String> possibleWords) {
        possibleWords.sort((String s1, String s2) -> s2.length() - s1.length());
    }

    private void sortByQuality(List<Board> boards) {
        boards.sort(Comparator.comparingDouble(boardEvaluator::evaluateBoard));
    }

}
