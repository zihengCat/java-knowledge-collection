package io.ziheng.tree.leetcode;
/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
/**
 * LeetCode 106. Construct Binary Tree from Inorder and Postorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        // ...
    }
    // Index
    private int pInorder;
    private int pPostorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0
            || postorder == null || postorder.length == 0
            || inorder.length != postorder.length) {
            return null;
        }
        this.pInorder = inorder.length - 1;
        this.pPostorder = postorder.length - 1;
        return buildTreeRecursively(inorder, postorder, null);
    }
    private TreeNode buildTreeRecursively(int[] inorder, int[] postorder, TreeNode end) {
        if (pPostorder < 0) {
            return null;
        }
        TreeNode node = new TreeNode(postorder[pPostorder]);
        pPostorder--;
        if (inorder[pInorder] != node.val) {
            node.right = buildTreeRecursively(inorder, postorder, node);
        }
        pInorder--;
        if ((end == null) || (inorder[pInorder] != end.val)) {
            node.left = buildTreeRecursively(inorder, postorder, end);
        }
        return node;
    }
}
/* EOF */