package com.animatinator.practice.dp.winesale;

import java.util.ArrayList;
import java.util.List;

class MaximumWineProfit {
    private static String BEGINNING = "beg";
    private static String END = "end";

    static Result maxProfit(List<Integer> wines) {
        return maxProfitR(wines, 1);
    }

    private static Result maxProfitR(List<Integer> wines, int multiplier) {
        if (wines.size() == 0) {
            return new Result(new ArrayList<>(), 0);
        } else if (wines.size() == 1) {
            List<String> orders = new ArrayList<>();
            orders.add(BEGINNING);
            return new Result(orders, multiplier * wines.get(0));
        }

        Result leftResult = maxProfitR(wines.subList(1, wines.size()), multiplier + 1);
        leftResult.total += multiplier * wines.get(0);
        leftResult.orders.add(0, BEGINNING);
        Result rightResult = maxProfitR(wines.subList(0, wines.size() - 1), multiplier + 1);
        rightResult.total += multiplier * wines.get(wines.size() - 1);
        rightResult.orders.add(0, END);

        if (leftResult.total > rightResult.total) {
            return leftResult;
        } else {
            return rightResult;
        }
    }

    static class Result {
        List<String> orders;
        int total;

        Result(List<String> orders, int total) {
            this.orders = orders;
            this.total = total;
        }
    }
}
