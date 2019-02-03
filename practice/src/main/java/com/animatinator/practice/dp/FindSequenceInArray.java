package com.animatinator.practice.dp;

class FindSequenceInArray {
    static boolean containsSequence(int[][] array, String sequence) {
        if (array.length == 0 || array[0].length == 0) {
            return false;
        }

        if (sequence.length() == 0) {
            return true;
        }

        int[][][] dp = new int[array.length][array[0].length][sequence.length()];

        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[0].length; x++) {
                if (containsSequence(array, dp, sequence, x, y, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean containsSequence(int[][] array, int[][][] dp, String sequence, int x, int y, int k) {
        if (x < 0 || y < 0 || x > array[0].length || y > array.length) {
            return false;
        }

        if (k >= sequence.length()) {
            return true;
        }

        if (dp[y][x][k] > 0) {
            return true;
        } else if (dp[y][x][k] < 0) {
            return false;
        }

        boolean matchesHere = array[y][x] == sequence.charAt(k);

        boolean result = matchesHere && (
                containsSequence(array, dp, sequence, x + 1, y, k + 1) ||
                        containsSequence(array, dp, sequence, x - 1, y, k + 1) ||
                        containsSequence(array, dp, sequence, x, y + 1, k + 1) ||
                        containsSequence(array, dp, sequence, x, y - 1, k + 1)
                );

        dp[y][x][k] = result ? 1 : -1;

        return result;
    }
}
