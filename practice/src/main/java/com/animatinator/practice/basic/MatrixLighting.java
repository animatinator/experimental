package com.animatinator.practice.basic;

class MatrixLighting {
    static int[][] lightMatrixFromPoints(int[][] matrix) {
        assert(matrix.length > 0);
        assert(matrix[0].length > 0);

        int[] litRows = new int[matrix.length];
        int[] litColumns = new int[matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    litRows[row] = 1;
                    litColumns[col] = 1;
                }
            }
        }

        return lightMatrix(matrix, litRows, litColumns);
    }

    private static int[][] lightMatrix(int[][] matrix, int[] litRows, int[] litColumns) {
        for (int row = 0; row < litRows.length; row++) {
            for (int col = 0; col < litColumns.length; col++) {
                if (litRows[row] == 1 || litColumns[col] == 1) {
                    matrix[row][col] = 1;
                }
            }
        }

        return matrix;
    }
}
