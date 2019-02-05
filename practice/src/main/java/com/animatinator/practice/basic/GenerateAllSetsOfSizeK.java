package com.animatinator.practice.basic;

import java.util.ArrayList;
import java.util.List;

class GenerateAllSetsOfSizeK {
    static List<List<Integer>> generateAllSetsOfSizeK(int k, int max) {
        return generateAllSetsOfSizeK(1, k, max);
    }

    private static List<List<Integer>> generateAllSetsOfSizeK(int min, int k, int max) {
        if (k == 0) {
            return null;
        }

        List<List<Integer>> sets = new ArrayList<>();

        for (int i = min; i <= (max - (k - 1)); i++) {
            List<List<Integer>> setsFromHere = generateAllSetsOfSizeK(i + 1, k - 1, max);
            if (setsFromHere == null) {
                ArrayList<Integer> newSet = new ArrayList<>();
                newSet.add(i);
                sets.add(newSet);
            } else {
                prependToAll(i, setsFromHere);
                sets.addAll(setsFromHere);
            }
        }

        return sets;
    }

    private static void prependToAll(int x, List<List<Integer>> sets) {
        for (List<Integer> set : sets) {
            set.add(0, x);
        }
    }
}
