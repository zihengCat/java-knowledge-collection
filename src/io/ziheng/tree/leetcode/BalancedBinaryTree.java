package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

/**
 * LeetCode 110. Balanced Binary Tree
 * https://leetcode.com/problems/balanced-binary-tree/submissions/
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
/* EOF */