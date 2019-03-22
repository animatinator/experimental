package com.animatinator.practice.treebottom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BottomViewTest {
    private BinaryTree firstExample;
    private BinaryTree crossoverTree;

    @Before
    public void setUp() {
        firstExample = new BinaryTree(20,
                new BinaryTree(8,
                        new BinaryTree(5, null, null),
                        new BinaryTree(3,
                                new BinaryTree(10, null, null),
                                new BinaryTree(14, null, null))),
                new BinaryTree(22,
                        null,
                        new BinaryTree(25, null, null)));
        crossoverTree = new BinaryTree(20,
                new BinaryTree(8,
                        new BinaryTree(5, null, null),
                        new BinaryTree(3,
                                new BinaryTree(10, null, null),
                                null)),
                new BinaryTree(22,
                        new BinaryTree(4,
                                null,
                                new BinaryTree(14, null, null)),
                        new BinaryTree(25, null, null)));
    }

    @Test
    public void firstExample() {
        assertArrayEquals(new int[]{5, 10, 3, 14, 25}, BottomView.bottomView(firstExample));
    }

    @Test
    public void crossoverTree() {
        // This tree has two nodes occupying the same horizontal space in the bottom view. In this case, the second one
        // in a left-to-right traversal should dominate.
        assertArrayEquals(new int[] {5, 10, 4, 14, 25}, BottomView.bottomView(crossoverTree));
    }
}