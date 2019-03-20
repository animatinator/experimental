package com.animatinator.practice.graphs.rottenoranges;

import java.util.*;

class RottenOranges {
    private static final int ROTTEN = 1;
    private static final int FRESH = 2;

    private static final Point SPACER = new Point(-1, -1);

    private static final class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x * x + y * y;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Point)) {
                return false;
            }
            Point otherPoint = (Point) other;
            return x == otherPoint.x && y == otherPoint.y;
        }
    }

    static int timeToRot(int[][] oranges) {
        assert(oranges.length > 0);
        assert(oranges[0].length > 0);

        int result = 0;

        Queue<Point> rottenQueue = new LinkedList<>();
        Set<Point> freshOranges = new HashSet<>();

        for (int y = 0; y < oranges.length; y++) {
            for (int x = 0; x < oranges[0].length; x++) {
                if (oranges[y][x] == ROTTEN) {
                    rottenQueue.add(new Point(x, y));
                } else if (oranges[y][x] == FRESH) {
                    freshOranges.add(new Point(x, y));
                }
            }
        }

        rottenQueue.add(SPACER);

        while (!rottenQueue.isEmpty()) {
            Point head = rottenQueue.poll();

            if (head == SPACER) {
                if (rottenQueue.isEmpty()) {
                    break;
                }
                rottenQueue.offer(SPACER);
                result++;
                continue;
            }

            assert head != null;
            List<Point> neighbours = getNeighbours(head, oranges[0].length, oranges.length);

            for (Point neighbour : neighbours) {
                if (oranges[neighbour.y][neighbour.x] == FRESH) {
                    oranges[neighbour.y][neighbour.x] = ROTTEN;
                    freshOranges.remove(neighbour);
                    rottenQueue.offer(neighbour);
                }
            }
        }

        if (freshOranges.isEmpty()) {
            return result;
        } else {
            return -1;
        }
    }

    private static List<Point> getNeighbours(Point p, int width, int height) {
        ArrayList<Point> neighbours = new ArrayList<>();

        if (p.y > 0) {
            neighbours.add(new Point(p.x, p.y - 1));
        }
        if (p.y < height - 1) {
            neighbours.add(new Point(p.x, p.y + 1));
        }
        if (p.x > 0) {
            neighbours.add(new Point(p.x - 1, p.y));
        }
        if (p.x < width - 1) {
            neighbours.add(new Point(p.x + 1, p.y));
        }

        return neighbours;
    }
}
