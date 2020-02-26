package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 98. Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
public class ValidateBinarySearchTree {
    public boolean isValidBSTv2(TreeNode root) {
        return isValidBSTRecursively(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean isValidBSTRecursively(
        TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBSTRecursively(root.left, min, root.val) &&
               isValidBSTRecursively(root.right, root.val, max);
    }
    private List<Integer> resultList = new ArrayList<>();
    public boolean isValidBSTv1(TreeNode root) {
        if (root == null) {
            return true;
        }
        inorderTraversal(root);
        if (resultList.size() <= 1) {
            return true;
        }
        for (int i = 1; i < resultList.size(); i++) {
            if (resultList.get(i) <= resultList.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        visitNode(root);
        inorderTraversal(root.right);
    }
    private void visitNode(TreeNode node) {
        resultList.add(node.val);
    }
}
/* EOF */