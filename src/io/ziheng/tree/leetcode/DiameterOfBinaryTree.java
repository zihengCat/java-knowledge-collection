package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

public class DiameterOfBinaryTree {
    private int maxVal = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        diameterOfBinaryTree0(root);
        return maxVal;
    }
    private int diameterOfBinaryTree0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = diameterOfBinaryTree0(root.left);
        int right = diameterOfBinaryTree0(root.right);
        this.maxVal = Math.max(maxVal, left + right);
        return 1 + Math.max(left, right);
    }
}
/* EOF */