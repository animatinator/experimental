package com.animatinator.practice.treebottom;

import java.util.*;

class BottomView {
    static int[] bottomView(BinaryTree tree) {
        Map<Integer, Integer> bottomView = new HashMap<>();
        Queue<BinaryTree> toVisit = new LinkedList<>();
        tree.xPos = 0;
        toVisit.add(tree);
        int minX = 0;

        while (!toVisit.isEmpty()) {
            BinaryTree head = toVisit.poll();
            assert(head != null);

            bottomView.put(head.xPos, head.value);
            minX = Math.min(minX, head.xPos);

            BinaryTree left = head.left;
            if (left != null) {
                left.xPos = head.xPos - 1;
                toVisit.offer(left);
            }
            BinaryTree right = head.right;
            if (right != null) {
                right.xPos = head.xPos + 1;
                toVisit.offer(right);
            }
        }

        int[] result = new int[bottomView.size()];
        for (Integer key : bottomView.keySet()) {
            result[key - minX] = bottomView.get(key);
        }

        return result;
    }
}
