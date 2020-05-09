package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

/**
 * LeetCode 993. Cousins in Binary Tree
 * https://leetcode.com/problems/cousins-in-binary-tree/
 */
public class CousinsInBinaryTree {
    private int xDepth = -1;
    private int yDepth = -1;
    private TreeNode xParent = null;
    private TreeNode yParent = null;
    public boolean isCousins(TreeNode root, int x, int y) {
        getDepthAndParent(root, null, 0, x, y);
        return xDepth == yDepth && xParent != yParent;
    }
    private void getDepthAndParent(
        TreeNode rootNode,
        TreeNode parentNode,
        int currentDepth,
        int xValue,
        int yValue) {
        if (rootNode == null) {
            return;
        }
        if (xValue == rootNode.val) {
            this.xDepth = currentDepth;
            this.xParent = parentNode;
        } else if (yValue == rootNode.val) {
            this.yDepth = currentDepth;
            this.yParent = parentNode;
        } else {
            getDepthAndParent(
                rootNode.left,
                rootNode,
                1 + currentDepth,
                xValue,
                yValue
            );
            getDepthAndParent(
                rootNode.right,
                rootNode,
                1 + currentDepth,
                xValue,
                yValue
            );
        }
    }
}
/* EOF */