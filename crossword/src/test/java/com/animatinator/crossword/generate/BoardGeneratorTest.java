package com.animatinator.crossword.generate;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.evaluate.BoardEvaluator;
import com.animatinator.crossword.evaluate.SimpleBoardEvaluator;
import com.animatinator.crossword.print.BoardPrinter;
import com.animatinator.crossword.print.SystemOutPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

@RunWith(JUnit4.class)
public class BoardGeneratorTest {
    private static final String[] WORDS = new String[]{
            "ads", "cue", "sad", "sea", "sue", "use", "aces", "case", "cues", "dues", "used", "cause", "sauce",
            "caused"};
    private static final int ITERATIONS = 100;

    @Test
    public void evaluateBoardGeneration() {
        BoardEvaluator evaluator = new SimpleBoardEvaluator();
        BoardGenerator generator = new BoardGenerator(evaluator);
        double[] results = new double[ITERATIONS];
        double best = -1.0d;
        Board bestBoard = null;

        for (int i = 0; i < ITERATIONS; i++) {
            Board board = generator.generateBoard(Arrays.asList(WORDS));
            double fitness = evaluator.evaluateBoard(board);
            results[i] = fitness;
            if (fitness > best) {
                bestBoard = board;
                best = fitness;
            }
        }

        System.out.println("Average board: "+getMean(results));
        System.out.println("Best board: "+best);
        new BoardPrinter(new SystemOutPrinter()).printBoard(bestBoard);
        System.out.println();
    }

    private static double getMean(double[] values) {
        double sum = 0.0d;

        for (double value : values) {
            sum += value;
        }

        return sum / (double)values.length;
    }
}