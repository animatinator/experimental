package com.animatinator.practice.binarytreesum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FindSummingNodesTest {
    @Test
    public void inOrder_works() {
        TreeNode root = new TreeNode(5);
        root.insert(3);
        root.insert(7);
        root.insert(2);
        root.insert(4);
        root.insert(8);
        root.insert(6);
        root.insert(9);
        root.insert(1);

        List<Integer> result = FindSummingNodes.inOrder(root);

        for (int i = 0; i < result.size(); i++) {
            assertEquals(i + 1, (int)result.get(i));
        }
    }

    @Test
    public void findSummingNodes() {
        TreeNode root = new TreeNode(15);
        root.insert(1);
        root.insert(9);
        root.insert(23);
        root.insert(150);
        root.insert(22);
        root.insert(21);
        root.insert(5);

        FindSummingNodes.NumPair result = FindSummingNodes.findSummingNodes(root, 31);
        assertTrue(result.equals(9, 22));
        assertNull(FindSummingNodes.findSummingNodes(root, 25));
    }

    @Test
    public void findSummingNodes_null() {
        assertNull(FindSummingNodes.findSummingNodes(null, 10));
    }

    @Test
    public void findSummingNodes_singleElement() {
        assertNull(FindSummingNodes.findSummingNodes(new TreeNode(5), 5));
    }

    @Test
    public void findSummingNodes_small() {
        TreeNode root = new TreeNode(7);
        root.insert(3);
        FindSummingNodes.NumPair result = FindSummingNodes.findSummingNodes(root, 10);
        assertTrue(result.equals(3, 7));
    }
}