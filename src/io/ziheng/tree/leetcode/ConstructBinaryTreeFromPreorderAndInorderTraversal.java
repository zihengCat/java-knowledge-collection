package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

/**
 * LeetCode 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree0(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }
    private TreeNode buildTree0(
        int[] pre, int[] in,
        int preStart, int preEnd,
        int inStart, int inEnd) {
        if (preStart > preEnd
            || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        int inRoot = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == pre[preStart]) {
                inRoot = i;
            }
        }
        int numsLeft = inRoot - inStart;
        root.left = buildTree0(
            pre, in,
            preStart + 1, preStart + numsLeft,
            inStart, inRoot - 1
        );
        root.right = buildTree0(
            pre, in,
            preStart + numsLeft + 1, preEnd,
            inRoot + 1, inEnd
        );
        return root;
    }
}
/* EOF */