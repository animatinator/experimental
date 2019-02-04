package com.animatinator.practice.dp.gamescores;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class GameScoresTest {
    @Test
    public void getNumberOfScoreCombinations() {
        int[] possibleScores = {1, 3, 5};

        assertEquals(2, GameScores.getNumberOfScoreCombinations(1, 1, possibleScores));
        assertEquals(6, GameScores.getNumberOfScoreCombinations(1, 3, possibleScores));
    }

    @Test
    public void getNumberOfPossibleLeadChanges() {
        int[] possibleScores = {1, 3, 5};

        assertEquals(2, GameScores.maximumNumberOfLeadChanges(1, 1, possibleScores));
        assertEquals(1, GameScores.maximumNumberOfLeadChanges(3, 1, possibleScores));
        assertEquals(2, GameScores.maximumNumberOfLeadChanges(1, 3, possibleScores));
    }
}