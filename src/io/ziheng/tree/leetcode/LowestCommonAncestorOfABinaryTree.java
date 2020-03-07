package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

/**
 * LeetCode 236. Lowest Common Ancestor of a Binary Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return root;
        }
        return lowestCommonAncestor0(root, p, q);
    }
    private TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor0(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor0(root.right, p, q);
        return leftNode == null ? rightNode :
            rightNode == null ? leftNode : root;
    }
}
/* EOF */