package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

/**
 * LeetCode 112. Path Sum
 * https://leetcode.com/problems/path-sum/
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSumRecursively(root, sum);
    }
    private boolean hasPathSumRecursively(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        return hasPathSumRecursively(root.left, sum - root.val)
            || hasPathSumRecursively(root.right, sum - root.val);
    }
}
/* EOF */