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
 * 1008. Construct Binary Search Tree from Preorder Traversal
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
    public static void main(String[] args) {
        ConstructBinarySearchTreeFromPreorderTraversal obj =
        new ConstructBinarySearchTreeFromPreorderTraversal();
        int[] preorder = new int[]{
            8, 5, 1, 7, 10, 12,
        };
        System.out.println(
            obj.bstFromPreorder(preorder)
        );
    }
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode rootNode = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            insertIntoBinarySearchTree(rootNode, preorder[i]);
        }
        return rootNode;
    }
    private void insertIntoBinarySearchTree(TreeNode root, int val) {
        if (root == null) {
            return;
        }
        // if (val == root.val) {
        // ...
        // }
        if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insertIntoBinarySearchTree(root.right, val);
            }
        }
        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insertIntoBinarySearchTree(root.left, val);
            }
        }
    }
}
/* EOF */