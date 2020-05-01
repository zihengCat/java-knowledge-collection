package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

/**
 * LeetCode ???. Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree
 * ...
 */
public class CheckIfAStringIsAValidSequenceFromRootToLeavesPathInABinaryTree {
    public boolean isValidSequence(TreeNode root, int[] arr) {
        if (root == null || arr == null || arr.length == 0) {
            return false;
        }
        return isValidSequence0(root, arr, 0);
    }
    private boolean isValidSequence0(TreeNode root, int[] arr, int currentIndex) {
        if (root == null || currentIndex == arr.length) {
            return false;
        }
        if (root.val != arr[currentIndex]) {
            return false;
        }
        if (root.left == null && root.right == null && currentIndex == arr.length - 1) {
            return true;
        }
        return isValidSequence0(root.left, arr, currentIndex + 1)
            || isValidSequence0(root.right, arr, currentIndex + 1);
    }
}
/* EOF */