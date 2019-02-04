package com.animatinator.practice.dp;

class EditDistance {
    static int getMinNumEdits(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    // First should be 3, 2
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Delete
                    int editsWithDelete = Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1);
                    // Replace
                    int editsWithReplace = dp[i-1][j-1] + 1;
                    dp[i][j] = Math.min(editsWithDelete, editsWithReplace);
                }
            }
        }

        return dp[a.length()][b.length()];
    }
}
