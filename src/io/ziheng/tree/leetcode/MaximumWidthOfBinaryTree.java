package io.ziheng.tree.leetcode;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {
        // ...
    }
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * LeetCode 662. Maximum Width of Binary Tree
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 */
public class MaximumWidthOfBinaryTree {
    public static void main(String[] args) {
        // ...
    }
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maximumWidth = 0;
        Queue<TreeNode> aQueue = new LinkedList<>();
        Queue<Integer> aQueueIndex = new LinkedList<>();
        aQueue.offer(root);
        aQueueIndex.offer(1);
        while (!aQueue.isEmpty()) {
            int size = aQueue.size();
            int widthStart = 0;
            int widthEnd = 0;
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = aQueue.poll();
                int currentIndex = aQueueIndex.poll();
                if (i == 0) {
                    widthStart = currentIndex;
                }
                if (i == size - 1) {
                    widthEnd = currentIndex;
                }
                if (currentNode.left != null) {
                    aQueue.offer(currentNode.left);
                    aQueueIndex.offer(2 * currentIndex);
                }
                if (currentNode.right != null) {
                    aQueue.offer(currentNode.right);
                    aQueueIndex.offer(2 * currentIndex + 1);
                }
            }
            maximumWidth = Math.max(maximumWidth, widthEnd - widthStart + 1);
        }
        return maximumWidth;
    }
}

/* EOF */