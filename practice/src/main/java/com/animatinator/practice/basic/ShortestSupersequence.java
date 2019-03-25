package com.animatinator.practice.basic;

class ShortestSupersequence {
    static int shortestSupersequence(String first, String second) {
        int[][] dp = new int[first.length() + 1][second.length() + 1];

        dp[0][0] = 0;

        for (int col = 1; col <= second.length(); col++) {
            dp[0][col] = col;
        }

        for (int row = 1; row <= first.length(); row++) {
            dp[row][0] = row;
        }

        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                int diagonalCost = dp[i-1][j-1] + ((first.charAt(i - 1) == second.charAt(j - 1)) ? 1 : 2);
                int verticalCost = dp[i-1][j] + 1;
                int horizontalCost = dp[i][j-1] + 1;
                dp[i][j] = Math.min(diagonalCost, Math.min(verticalCost, horizontalCost));
            }
        }

        return dp[first.length()][second.length()];
    }
}
