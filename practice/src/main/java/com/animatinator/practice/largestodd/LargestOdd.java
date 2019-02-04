package com.animatinator.practice.largestodd;

import java.util.ArrayList;
import java.util.List;

class LargestOdd {
    // This pair of arrays defines the set of neighbour directions from a given point.
    // The idea is to iterate through indices from 1 to 8 and pick points (xOffsets[i], yOffsets[i]).
    private static final int[] xOffsets = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int[] yOffsets = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};

    static int findLargestOddNumberAboveTenAlongMatrixRow(int[][] matrix) {
        assert(matrix.length > 0);
        assert(matrix[0].length > 0);
        for (int[] row : matrix) {
            for (int element : row) {
                // Only numbers from 1 to 9 (arbitrary restriction)
                assert (element > 0 && element < 10);
            }
        }

        int best = 0;

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                for (int i = 0; i < 8; i++) {
                    int bestHere = findLargestOddNumberFromPointInDirection(matrix, x, y, xOffsets[i], yOffsets[i]);
                    best = Math.max(best, bestHere);
                }
            }
        }

        return best;
    }

    private static int findLargestOddNumberFromPointInDirection(int[][] matrix, int x, int y, int dx, int dy) {
        List<Integer> intInProgress = new ArrayList<>();
        int max = 0;

        while (x >= 0 && x < matrix[0].length && y >= 0 && y < matrix.length) {
            intInProgress.add(matrix[y][x]);

            if (intInProgress.size() >= 2) {
                int curNum = toNum(intInProgress);
                if (isOdd(curNum)) {
                    // We're adding more digits each time and none are zero, so the new odd is always maximal.
                    max = curNum;
                }
            }

            x += dx;
            y += dy;
        }

        return max;
    }

    private static int toNum(List<Integer> integerList) {
        int factor = 1;
        int total = 0;

        for (int i = integerList.size() - 1; i >= 0; i--) {
            total += factor * integerList.get(i);
            factor *= 10;
        }

        return total;
    }

    private static boolean isOdd(int number) {
        return (number % 2) == 1;
    }
}
