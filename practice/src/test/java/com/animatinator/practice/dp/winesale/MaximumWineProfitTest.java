package com.animatinator.practice.dp.winesale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MaximumWineProfitTest {
    @Test
    public void emptyList() {
        MaximumWineProfit.Result result = MaximumWineProfit.maxProfit(new ArrayList<>());

        assertEquals(0, result.total);
        assertTrue(result.orders.isEmpty());
    }

    @Test
    public void hobsonsChoice() {
        MaximumWineProfit.Result result = MaximumWineProfit.maxProfit(Collections.singletonList(27));

        assertEquals(27, result.total);
        assertEquals(1, result.orders.size());
    }

    @Test
    public void example() {
        MaximumWineProfit.Result result = MaximumWineProfit.maxProfit(Arrays.asList(2, 4, 6, 2, 5));

        assertEquals(64, result.total);
        assertListsEqual(Arrays.asList("beg", "end", "end", "beg", "beg"), result.orders);
    }

    private <T> void assertListsEqual(List<T> first, List<T> second) {
        assertEquals(first.size(), second.size());

        for (int i = 0; i < first.size(); i++) {
            assertEquals(first.get(i), second.get(i));
        }
    }
}