package io.ziheng.tree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * LeetCode 404. Sum of Left Leaves
 * https://leetcode.com/problems/sum-of-left-leaves/
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // return sumOfLeftLeavesRecursively(root, false);
        return sumOfLeftLeavesIteratively(root);
    }
    private int sumOfLeftLeavesRecursively(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null
            && isLeft) {
            return root.val;
        }
        return sumOfLeftLeavesRecursively(root.left, true)
            + sumOfLeftLeavesRecursively(root.right, false);
    }
    private int sumOfLeftLeavesIteratively(TreeNode root) {
        int result = 0;
        Queue<Pair<TreeNode, Boolean>> aQueue = new LinkedList<>();
        aQueue.offer(new Pair<TreeNode, Boolean>(root, false));
        while (!aQueue.isEmpty()) {
            Pair<TreeNode, Boolean> pair = aQueue.poll();
            TreeNode node = pair.getLeft();
            if (isLeave(node) && pair.getRight()) {
                result += node.val;
            }
            if (node.left != null) {
                aQueue.offer(new Pair<TreeNode, Boolean>(node.left, true));
            }
            if (node.right != null) {
                aQueue.offer(new Pair<TreeNode, Boolean>(node.right, false));
            }
        }
        return result;
    }
    private boolean isLeave(TreeNode node) {
        return node != null
            ? node.left == null && node.right == null
            : false;
    }
    private class Pair<L, R> {
        private L left;
        private R right;
        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }
        public L getLeft() {
            return left;
        }
        public R getRight() {
            return right;
        }
    }
}
/* EOF */