package com.animatinator.practice.dp.wordwrap;

import java.util.ArrayList;
import java.util.List;

class WordWrapper {
    static Result wordWrap(String input, int width) {
        String[] words = getWords(input);
        DpEntry[] bestScores = new DpEntry[words.length];

        for (int i = 0; i < words.length; i++) {
            int currentLineWidth = words[i].length();
            int costIfEndingAtWordI = (int)Math.pow(width - currentLineWidth, 2);
            int lineStart = i;

            for (int j = i - 1; j >= 0; j--) {
                int widthIncludingWordJ = currentLineWidth + words[j].length() + 1;
                if (widthIncludingWordJ <= width) {
                    currentLineWidth = widthIncludingWordJ;
                    int costForLineWithWordJ = (int)Math.pow((width - currentLineWidth), 2);
                    if (j > 0 && j != i) {
                        // Also include the cost for the previous line.
                        costForLineWithWordJ += bestScores[j - 1].cost;
                    }
                    if (costForLineWithWordJ < costIfEndingAtWordI) {
                        costIfEndingAtWordI = costForLineWithWordJ;
                        lineStart = j;
                    }
                } else {
                    break;
                }
            }

            bestScores[i] = new DpEntry(costIfEndingAtWordI, lineStart);
        }

        return constructResult(words, bestScores);
    }

    private static Result constructResult(String[] words, DpEntry[] dp) {
        int cost = dp[dp.length - 1].cost;

        List<String> lines = new ArrayList<>();

        for (int i = dp.length - 1; i >= 0;) {
            int parent = dp[i].lineStart;
            StringBuilder thisLine = new StringBuilder();

            for (int j = parent; j <= i; j++) {
                thisLine.append(words[j]);
                // Intersperse with spaces.
                if (j != i) {
                    thisLine.append(" ");
                }
            }

            // Intersperse lines with newline characters.
            if (i != (dp.length - 1)) {
                thisLine.append("\n");
            }
            lines.add(thisLine.toString());

            i = parent - 1;
        }

        // Append together the strings in reverse order.
        StringBuilder result = new StringBuilder();
        for (int i = lines.size() - 1; i >= 0; i--) {
            result.append(lines.get(i));
        }

        return new Result(cost, result.toString());
    }

    private static String[] getWords(String input) {
        return input.split(" ");
    }

    static class Result {
        int cost;
        String result;

        Result(int cost, String result) {
            this.cost = cost;
            this.result = result;
        }
    }

    private static class DpEntry {
        int cost;
        int lineStart;

        DpEntry(int cost, int lineStart) {
            this.cost = cost;
            this.lineStart = lineStart;
        }
    }
}
