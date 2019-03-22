package com.animatinator.practice.nutsnbolts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MatchNutsToBoltsTest {
    @Test
    public void firstExample() {
        String[] nuts = "@ % $ # ^".split(" ");
        String[] bolts = "% @ # $ ^ ^".split(" ");
        String resultLine = "@ # $ % ^";
        String expected = String.format(" %s\n %s", resultLine, resultLine);

        String result = MatchNutsToBolts.matchNutsToBolts(nuts, bolts);
        assertEquals(expected, result);
    }

    @Test
    public void secondExample() {
        String[] nuts = "^ & % @ # * $ ~ !".split(" ");
        String[] bolts = "~ # @ % & * $ ^ !".split(" ");
        String resultLine = "@ ! # $ % & * ^ ~";
        String expected = String.format(" %s\n %s", resultLine, resultLine);

        String result = MatchNutsToBolts.matchNutsToBolts(nuts, bolts);
        assertEquals(expected, result);
    }
}