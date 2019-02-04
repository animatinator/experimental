package com.animatinator.practice.graphs.topsort;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TopologicalSortTest {
    @Test
    public void simpleTest() {
        Node buildUrl = new Node("buildUrl");
        Node getLocation = new Node("location");
        Node fetch = new Node("fetch");
        Node render = new Node("render");
        Node actions = new Node("actions");
        Node endstateLogging = new Node("endstate");

        endstateLogging.addDependency(render);
        endstateLogging.addDependency(actions);
        render.addDependency(fetch);
        actions.addDependency(fetch);
        fetch.addDependency(buildUrl);
        fetch.addDependency(getLocation);

        TopologicalSort sorter =
                new TopologicalSort(Arrays.asList(endstateLogging, render, actions, getLocation, buildUrl, fetch));

        List<Node> result = sorter.topologicalSort();

        // The order here is just the one the sorter happened to produce given the order in which things were inserted
        // etc., but it's a valid ordering.
        assertContainsEntriesInOrder(result, "location", "buildUrl", "fetch", "render", "actions", "endstate");
    }

    private void assertContainsEntriesInOrder(List<Node> result, String ... entries) {
        assertEquals(result.size(), entries.length);

        for (int i = 0; i < entries.length; i++) {
            assertEquals(entries[i], result.get(i).name);
        }
    }
}