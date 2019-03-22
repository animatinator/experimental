package com.animatinator.practice.nutsnbolts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MatchNutsToBolts {
    private static final class SolutionTree {
        boolean isLeaf;
        String[] elementsHere;
        String value = null;
        SolutionTree left = null;
        SolutionTree right = null;

        SolutionTree(String[] elementsHere) {
            isLeaf = true;
            this.elementsHere = elementsHere;
        }

        void splitOff(String value, SolutionTree left, SolutionTree right) {
            isLeaf = false;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static String matchNutsToBolts(String[] nuts, String[] bolts) {
        Map<String, String> matching = new HashMap<>();
        SolutionTree solutionTree = new SolutionTree(bolts);

        for (String nut : nuts) {
            String matchingBolt = matchNutToBolt(nut, solutionTree);
            matching.put(nut, matchingBolt);
        }

        return solutionAsString(matching);
    }

    private static String solutionAsString(Map<String, String> matching) {
        StringBuilder nuts = new StringBuilder();
        StringBuilder bolts = new StringBuilder();

        for (String key : matching.keySet()) {
            nuts.append(" ").append(key);
            bolts.append(" ").append(matching.get(key));
        }

        nuts.append("\n");
        nuts.append(bolts.toString());
        return nuts.toString();
    }

    private static String matchNutToBolt(String nut, SolutionTree solutionTree) {
        while (!solutionTree.isLeaf) {
            if (nut.equals(solutionTree.value)) {
                return solutionTree.value;
            } else if (nut.compareTo(solutionTree.value) < 0) {
                solutionTree = solutionTree.left;
            } else {
                solutionTree = solutionTree.right;
            }
        }

        List<String> smallerThan = new ArrayList<>();
        List<String> greaterThan = new ArrayList<>();
        String matchingValue = null;

        for (String bolt : solutionTree.elementsHere) {
            if (nut.equals(bolt)) {
                matchingValue = bolt;
            } else if (nut.compareTo(bolt) < 0) {
                greaterThan.add(bolt);
            } else {
                smallerThan.add(bolt);
            }
        }

        assert(matchingValue != null);

        solutionTree.splitOff(
                matchingValue,
                new SolutionTree(smallerThan.toArray(new String[0])),
                new SolutionTree(greaterThan.toArray(new String[0])));

        return matchingValue;
    }
}
