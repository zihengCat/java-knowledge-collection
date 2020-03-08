package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return minDepth0(root);
    }
    private int minDepth0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth0(root.left);
        int right = minDepth0(root.right);
        return left == 0 || right == 0
        ? 1 + left + right
        : 1 + Math.min(left, right);
    }
}
/* EOF */