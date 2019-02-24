package com.animatinator.practice.basic;

import java.util.*;

class GridIllumination {
    private static final class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) return true;
            if (!(other instanceof Point)) return false;
            Point otherPoint = (Point) other;
            return x == otherPoint.x && y == otherPoint.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static final class Lamp {
        int x;
        int y;
        boolean isOn;

        Lamp(int x, int y) {
            this.x = x;
            this.y = y;
            this.isOn = true;
        }
    }

    static int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        assert(N > 0);

        Map<Integer, List<Lamp>> xMap = new HashMap<>();
        Map<Integer, List<Lamp>> yMap = new HashMap<>();
        Map<Point, List<Lamp>> tlDiagonalMap = new HashMap<>();
        Map<Point, List<Lamp>> trDiagonalMap = new HashMap<>();
        Map<Point, Lamp> gridMap = new HashMap<>();

        for (int[] lampDef : lamps) {
            Lamp lamp = new Lamp(lampDef[0], lampDef[1]);
            updateMap(xMap, lamp.x, lamp);
            updateMap(yMap, lamp.y, lamp);
            updateMap(tlDiagonalMap, getTlDiagonalRepr(lamp.x, lamp.y), lamp);
            updateMap(trDiagonalMap, getTrDiagonalRepr(lamp.x, lamp.y, N), lamp);
            gridMap.put(new Point(lamp.x, lamp.y), lamp);
        }

        int[] answers = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            Point queryPoint = new Point(query[0], query[1]);

            answers[i] = 0;

            List<Lamp> possibleLamps = new ArrayList<>();
            List<Lamp> xLamp = xMap.get(queryPoint.x);
            if (xLamp != null) {
                possibleLamps.addAll(xLamp);
            }

            List<Lamp> yLamp = yMap.get(queryPoint.y);
            if (yLamp != null) {
                possibleLamps.addAll(yLamp);
            }

            List<Lamp> tlDiagLamp = tlDiagonalMap.get(getTlDiagonalRepr(queryPoint.x, queryPoint.y));
            if (tlDiagLamp != null) {
                possibleLamps.addAll(tlDiagLamp);
            }

            List<Lamp> trDiagLamp = trDiagonalMap.get(getTrDiagonalRepr(queryPoint.x, queryPoint.y, N));
            if (trDiagLamp != null) {
                possibleLamps.addAll(trDiagLamp);
            }

            for (Lamp possibleLamp : possibleLamps) {
                if (possibleLamp.isOn) {
                    answers[i] = 1;
                }
            }

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int x = queryPoint.x + dx;
                    int y = queryPoint.y + dy;
                    if (x >= 0 && y >= 0 && x < N && y < N) {
                        Lamp lampHere = gridMap.get(new Point(x, y));
                        if (lampHere != null) {
                            lampHere.isOn = false;
                        }
                    }
                }
            }
        }

        return answers;
    }

    // 'Normalise' points on top-left diagonals by finding the top-left point on the associated
    // diagonal - i.e. (0, N) or (N, 0).
    private static Point getTlDiagonalRepr(int x, int y) {
        int min = Math.min(x, y);
        return new Point(x - min, y - min);
    }

    // Similar but for the top-right diagonal.
    private static Point getTrDiagonalRepr(int x, int y, int N) {
        int min = Math.min(N - x, y);
        return new Point(x + min, y - min);
    }

    private static <T> void updateMap(Map<T, List<Lamp>> map, T key, Lamp newValue) {
        if (map.get(key) != null) {
            map.get(key).add(newValue);
        } else {
            List<Lamp> newList = new ArrayList<>();
            newList.add(newValue);
            map.put(key, newList);

        }
    }
}
