package com.animatinator.practice.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SumOfKInts {
    static List<Integer> kIntSum(int[] integers, int k, int target) {
        assert(k > 0);

        Arrays.sort(integers);

        return kIntSum(integers, 0, integers.length - 1, k, target);
    }

    private static List<Integer> kIntSum(int[] integers, int l, int r, int k, int target) {
        if (k == 1) {
            return singleIntSum(integers, l, r, target);
        } else if (k == 2) {
            return doubleIntSum(integers, l, r, target);
        }

        for (int i = l; i < (r - (k - 1)); i++) {
            List<Integer> maybeSolution = kIntSum(integers, i + 1, r, k - 1, target - integers[i]);
            if (maybeSolution != null) {
                ArrayList<Integer> solution = new ArrayList<>(maybeSolution);
                solution.add(integers[i]);
                return solution;
            }
        }

        return null;
    }

    private static List<Integer> singleIntSum(int[] integers, int l, int r, int target) {
        for (int i = l; i < r; i++) {
            if (integers[i] == target) {
                return Collections.singletonList(target);
            }
        }

        return null;
    }

    private static List<Integer> doubleIntSum(int[] integers, int l, int r, int target) {
        while (r > l) {
            int sumHere = integers[l] + integers[r];

            if (sumHere == target) {
                return Arrays.asList(integers[r], integers[l]);
            } else if (sumHere < target) {
                l++;
            } else {
                r--;
            }
        }

        return null;
    }
}
