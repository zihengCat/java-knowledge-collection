package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 404. Sum of Left Leaves
 * https://leetcode.com/problems/sum-of-left-leaves/
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //return sumOfLeftLeavesRecursively(root, false);
        return sumOfLeftLeavesIteratively(root);
    }
    private int sumOfLeftLeavesRecursively(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null && isLeft) {
            return root.val;
        }
        return sumOfLeftLeavesRecursively(root.left, true)
            + sumOfLeftLeavesRecursively(root.right, false);
    }
    private int sumOfLeftLeavesIteratively(TreeNode root) {
        int result = 0;
        Queue<Pair<TreeNode, Boolean>> queue = new LinkedList<>();
        queue.offer(new Pair<TreeNode, Boolean>(root, false));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Boolean> pair = queue.poll();
            TreeNode node = pair.getLeft();
            if (isLeaveNode(node) && pair.getRight()) {
                result += node.val;
            }
            if (node.left != null) {
                queue.offer(new Pair<TreeNode, Boolean>(node.left, true));
            }
            if (node.right != null) {
                queue.offer(new Pair<TreeNode, Boolean>(node.right, false));
            }
        }
        return result;
    }
    private boolean isLeaveNode(TreeNode node) {
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