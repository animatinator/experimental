package com.animatinator.practice.graphs;

import java.util.*;

class FloodFill {
    static void floodFill(boolean[][] world, int x, int y) {
        assert(x >= 0 && y >= 0);
        assert(y < world.length);
        assert(world.length > 0);
        assert(x < world[0].length);

        int height = world.length;
        int width = world[0].length;
        boolean baseColour = world[y][x];
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Set<Pair<Integer, Integer>> seen = new HashSet<>();
        queue.add(new Pair<>(x, y));

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> current = queue.poll();
            assert(current != null);

            if (!seen.contains(current)
                    && isInRange(current, width, height)
                    && world[current.y][current.x] == baseColour) {
                world[current.y][current.x] = !baseColour;
                queue.addAll(getNeighbours(current.x, current.y));
            }
            seen.add(current);
        }
    }

    private static boolean isInRange(Pair<Integer, Integer> point, int width, int height) {
        return point.x >= 0 && point.y >= 0 && point.x < width && point.y < height;
    }

    private static List<Pair<Integer, Integer>> getNeighbours(int x, int y) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        result.add(new Pair<>(x - 1, y));
        result.add(new Pair<>(x + 1, y));
        result.add(new Pair<>(x, y + 1));
        result.add(new Pair<>(x, y - 1));
        return result;
    }

    private static class Pair<X, Y> {
        final X x;
        final Y y;

        Pair(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}
