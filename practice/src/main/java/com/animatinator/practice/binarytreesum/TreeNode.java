package com.animatinator.practice.binarytreesum;

public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int value) {
        this.value = value;
    }

    void insert(int newVal) {
        if (newVal < value) {
            if (left == null) {
                left = new TreeNode(newVal);
            } else {
                left.insert(newVal);
            }
        } else {
            if (right == null) {
                right = new TreeNode(newVal);
            } else {
                right.insert(newVal);
            }
        }
    }
}
