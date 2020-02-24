package io.ziheng.tree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

import io.ziheng.tree.leetcode.TreeNode;

public class SearchInABinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        //return searchBSTRecursively(root, val);
        return searchBSTIteratively(root, val);
    }
    private TreeNode searchBSTIteratively(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (val == currentNode.val) {
                return currentNode;
            } else if (val > currentNode.val && currentNode.right != null) {
                queue.offer(currentNode.right);
            } else if (val < currentNode.val && currentNode.left != null) {
                queue.offer(currentNode.left);
            }
        }
        return null;
    }
    private TreeNode searchBSTRecursively(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val > root.val) {
            return searchBSTRecursively(root.right, val);
        } else if (val < root.val) {
            return searchBSTRecursively(root.left, val);
        } else {
            return root;
        }
    }
}

/* EOF */