package com.animatinator.practice.dp.gamescores;

class GameScores {
    static int getNumberOfScoreCombinations(int scoreA, int scoreB, int[] possibleScores) {
        int[][] dp = new int[scoreA + 1][scoreB + 1];

        dp[0][0] = 1;

        for (int i = 0; i <= scoreA; i++) {
            for (int j = 0; j <= scoreB; j++) {
                for (int scoreIncrement : possibleScores) {
                    if (i >= scoreIncrement) {
                        dp[i][j] += dp[i - scoreIncrement][j];
                    }
                    if (j >= scoreIncrement) {
                        dp[i][j] += dp[i][j - scoreIncrement];
                    }
                }
            }
        }

        return dp[scoreA][scoreB];
    }

    static int maximumNumberOfLeadChanges(int scoreA, int scoreB, int[] possibleScores) {
        int[][] dp = new int[scoreA + 1][scoreB + 1];

        for (int i = 0; i <= scoreA; i++) {
            for (int j = 0; j <= scoreB; j++) {
                for (int scoreIncrement : possibleScores) {
                    if (i > j) {
                        if (i >= scoreIncrement && (i - scoreIncrement <= j)) {
                            dp[i][j] = dp[i - scoreIncrement][j] + 1;
                        }
                    } else {
                        if (j >= scoreIncrement && (j - scoreIncrement < i)) {
                            dp[i][j] = dp[i][j - scoreIncrement] + 1;
                        }
                    }
                }
            }
        }

        return dp[scoreA][scoreB];
    }
}
