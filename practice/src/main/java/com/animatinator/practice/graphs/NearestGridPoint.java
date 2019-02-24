package com.animatinator.practice.graphs;

/**
 * Find the nearest non-zero integer in a grid by expanding diamonds around the source.
 */
class NearestGridPoint {
    static int nearestNonZero(int[][] grid, int x, int y) {
        if (grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid has a zero-size dimension!");
        } else if (y >= grid.length || x >= grid[0].length) {
            throw new IllegalArgumentException("Point is outside of grid!");
        }

        // Go as far as twice the furthest axis-aligned distance to the edge, to allow for diagonals.
        int maxDist = Math.max(Math.max(x, y), Math.max((grid.length - 1) - y, (grid[0].length - 1) - x)) * 2;

        // Diamonds of increasing size
        for (int size = 0; size <= maxDist; size++) {
            // xSize + ySize = diamond size. Increase xSize from 0 to size to cover all points along the diagonal edge.
            for (int xSize = 0; xSize <= size; xSize++) {
                int ySize = size - xSize;

                // Find the equivalent points along each of the four diagonals.
                int topRight = getPoint(grid, x + xSize, y - ySize);
                int bottomRight = getPoint(grid, x + xSize, y + ySize);
                int topLeft = getPoint(grid, x - xSize, y - ySize);
                int bottomLeft = getPoint(grid, x - xSize, y + ySize);

                if (topRight != 0) return topRight;
                if (bottomRight != 0) return bottomRight;
                if (topLeft != 0) return topLeft;
                if (bottomLeft != 0) return bottomLeft;
            }
        }

        return 0;
    }

    private static int getPoint(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || y >= grid.length || x >= grid[0].length) {
            return 0;
        }

        return grid[y][x];
    }
}
