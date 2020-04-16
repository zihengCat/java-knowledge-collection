package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

/**
 * LeetCode 129. Sum Root to Leaf Numbers
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 */
public class SumRootToLeafNumbers {
    private int sumVal = 0;
    public int sumNumbers(TreeNode root) {
        sumNumbers0(root, 0);
        return sumVal;
    }
    private void sumNumbers0(TreeNode rootNode, int currentSum) {
        if (rootNode == null) {
            return;
        }
        if (rootNode.left == null && rootNode.right == null) {
            sumVal += currentSum * 10 + rootNode.val;
        }
        sumNumbers0(rootNode.left, currentSum * 10 + rootNode.val);
        sumNumbers0(rootNode.right, currentSum * 10 + rootNode.val);
    }
}
/* EOF */