package com.animatinator.practice.binarytreesum;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class FindSummingNodes {
    static class NumPair {
        int first;
        int second;

        NumPair(int f, int s) {
            first = f;
            second = s;
        }

        boolean equals(int f, int s) {
            return f == first && s == second;
        }
    }

    static List<Integer> inOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (true) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                TreeNode top = stack.pop();
                result.add(top.value);
                current = top.right;
            }
        }

        return result;
    }

    static NumPair findSummingNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        int lVal = -1;
        int rVal = -1;
        TreeNode left = root;
        TreeNode right = root;
        Stack<TreeNode> lStack = new Stack<>();
        Stack<TreeNode> rStack = new Stack<>();

        while (left != null) {
            lVal = left.value;
            lStack.push(left);
            left = left.left;
        }

        while (right != null) {
            rVal = right.value;
            rStack.push(right);
            right = right.right;
        }

        while (true) {
            int sum = lVal + rVal;

            if (lVal > rVal) {
                return null;
            }

            if (sum == target) {
                return new NumPair(lVal, rVal);
            } else if (sum < target) {
                boolean done = false;
                while (!done) {
                    if (left != null) {
                        lStack.push(left);
                        left = left.left;
                    } else {
                        if (lStack.isEmpty()) {
                            return null;
                        }
                        left = lStack.pop();
                        lVal = left.value;
                        left = left.right;
                        done = true;
                    }
                }
            } else {
                boolean done = false;
                while (!done) {
                    if (right != null) {
                        rStack.push(right);
                        right = right.right;
                    } else {
                        if (rStack.isEmpty()) {
                            return null;
                        }
                        right = rStack.pop();
                        rVal = right.value;
                        right = right.left;
                        done = true;
                    }
                }
            }
        }
    }
}
