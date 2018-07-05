package com.animatinator.crossword.generate;

import com.animatinator.crossword.board.Board;
import com.animatinator.crossword.evaluate.BoardEvaluator;
import com.animatinator.crossword.evaluate.SimpleBoardEvaluator;
import com.animatinator.crossword.print.BoardPrinter;
import com.animatinator.crossword.print.SystemOutPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RunWith(JUnit4.class)
public class BoardGeneratorTest {
    private static final String[] WORDS = new String[]{
            "ads", "cue", "sad", "sea", "sue", "use", "aces", "case", "cues", "dues", "used", "cause", "sauce",
            "caused"};
    private static final int ITERATIONS = 100;

    @Test
    public void evaluateDefaultBoardGeneration() {
        BoardEvaluator evaluator = new SimpleBoardEvaluator();
        BoardGenerationFlags flags = new BoardGenerationFlags();
        BoardGenerator generator = new BoardGenerator(evaluator, flags);
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

        System.out.println("Evaluating the default board generation method:");
        System.out.println("Average board: "+getMean(results));
        System.out.println("Best board: "+best);
        new BoardPrinter(new SystemOutPrinter()).printBoard(bestBoard);
        System.out.println();
    }

    /**
     * This comparison is meaningless because the random initial orientation doesn't affect the score, and there's
     * nothing else random enabled.
     */
    @Test
    public void compareSingleBoardWithSeveral() {
        BoardGenerationFlags flagsWithMultiGenerationEnabled = new BoardGenerationFlags();
        flagsWithMultiGenerationEnabled.setFlag(BoardGenerationFlagConstant.GENERATE_SEVERAL_BOARDS, true);
        compareFlagSets(new BoardGenerationFlags(), flagsWithMultiGenerationEnabled);
    }

    /**
     * Picking randomly from the top few word placements at each stage seems to be better than just picking the best
     * every time.
     */
    @Test
    public void compareRandomWordSelectionWithBasic() {
        BoardGenerationFlags flagsWithRandomGeneration = new BoardGenerationFlags();
        flagsWithRandomGeneration.setFlag(BoardGenerationFlagConstant.PICK_RANDOMLY_FROM_BEST_FEW_WORD_PLACEMENTS, true);
        compareFlagSets(new BoardGenerationFlags(), flagsWithRandomGeneration);
    }

    /**
     * Generating several boards and picking the best seems to improve the mean generated board where we're selecting
     * words randomly.
     */
    @Test
    public void compareSingleBoardWithSeveral_randomWordSelection() {
        BoardGenerationFlags flagsWithMultiGenerationEnabled = new BoardGenerationFlags();
        flagsWithMultiGenerationEnabled.setFlag(BoardGenerationFlagConstant.GENERATE_SEVERAL_BOARDS, true);
        flagsWithMultiGenerationEnabled.setFlag(BoardGenerationFlagConstant.PICK_RANDOMLY_FROM_BEST_FEW_WORD_PLACEMENTS, true);

        BoardGenerationFlags justRandomWordSelection = new BoardGenerationFlags();
        justRandomWordSelection.setFlag(BoardGenerationFlagConstant.PICK_RANDOMLY_FROM_BEST_FEW_WORD_PLACEMENTS, true);

        compareFlagSets(flagsWithMultiGenerationEnabled, justRandomWordSelection);
    }

    private void compareFlagSets(BoardGenerationFlags ... flags) {
        System.out.println("Flag set comparison:");
        List<FitnessResult> results = evaluateGenerationWithFlags(flags);
        results.sort(Comparator.comparingDouble(FitnessResult::getMean).reversed());
        for (FitnessResult result : results) {
            System.out.println("Flags: "+result.getFlags()+"\n\tMean fitness: "+result.getMean()+"\n\tMax fitness: "+ result.getMax());
        }
        System.out.println();
    }

    private List<FitnessResult> evaluateGenerationWithFlags(BoardGenerationFlags ... flags) {
        List<FitnessResult> results = new ArrayList<>();

        for (BoardGenerationFlags flagSet : flags) {
            results.add(evaluateBoardGeneration(flagSet));
        }

        return results;
    }

    private FitnessResult evaluateBoardGeneration(BoardGenerationFlags flags) {
        BoardEvaluator evaluator = new SimpleBoardEvaluator();
        BoardGenerator generator = new BoardGenerator(evaluator, flags);
        double sum = 0.0d;
        double best = -1.0d;

        for (int i = 0; i < ITERATIONS; i++) {
            Board board = generator.generateBoard(Arrays.asList(WORDS));
            double fitness = evaluator.evaluateBoard(board);
            sum += fitness;
            best = Math.max(best, fitness);
        }

        return new FitnessResult(flags, sum / ITERATIONS, best);
    }

    private static double getMean(double[] values) {
        double sum = 0.0d;

        for (double value : values) {
            sum += value;
        }

        return sum / (double)values.length;
    }

    private static class FitnessResult {
        private final BoardGenerationFlags flags;
        private final double mean;
        private final double max;

        FitnessResult(BoardGenerationFlags flags, double mean, double max) {
            this.flags = flags;
            this.mean = mean;
            this.max = max;
        }

        double getMean() {
            return mean;
        }

        double getMax() {
            return max;
        }

        BoardGenerationFlags getFlags() {
            return flags;
        }
    }
}