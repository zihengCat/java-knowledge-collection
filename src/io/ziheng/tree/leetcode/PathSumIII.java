package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

/**
 * LeetCode 437. Path Sum III
 * https://leetcode.com/problems/path-sum-iii/
 */
public class PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSum0(root, sum)
            + pathSum(root.left, sum)
            + pathSum(root.right, sum);
    }
    private int pathSum0(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return (root.val == sum ? 1 : 0)
        + pathSum0(root.left, sum - root.val)
        + pathSum0(root.right, sum - root.val);
    }
}
/* EOF */