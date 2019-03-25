package com.animatinator.practice.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class GenerateIPs {
    static List<String> generateValidIPs(String input) {
        return generatePunctuatedStrings(input, 3);
    }

    private static List<String> generatePunctuatedStrings(String base, int points) {
        if (points == 0) {
            return Collections.singletonList(base);
        }
        assert(base.length() > points);

        List<String> results = new ArrayList<>();

        for (int division = 1; division <= (base.length() - points); division++) {
            String preDivision = base.substring(0, division);
            for (String result : generatePunctuatedStrings(base.substring(division, base.length()), points - 1)) {
                results.add(preDivision + "." + result);
            }
        }

        return results;
    }
}
