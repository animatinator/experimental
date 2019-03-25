package com.animatinator.practice.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;

@RunWith(JUnit4.class)
public class GenerateIPsTest {
    @Test
    public void example() {
        String example = "11211";
        String[] expected = new String[] {
                "1.1.2.11",
                "1.1.21.1",
                "1.12.1.1",
                "11.2.1.1"
        };

        List<String> results = GenerateIPs.generateValidIPs(example);
        assertArrayEquals(expected, results.toArray());
    }

    @Test
    public void onlyOneOption() {
        String example = "1111";
        String[] expected = new String[] {"1.1.1.1"};

        assertArrayEquals(expected, GenerateIPs.generateValidIPs(example).toArray());
    }
}