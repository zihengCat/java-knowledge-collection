package io.ziheng.tree.leetcode;

import java.util.List;
import java.util.Queue;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {
    }
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * LeetCode 107. Binary Tree Level Order Traversal II
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/ 
 */
public class BinaryTreeLevelOrderTraversalII {
    public static void main(String[] args) {
        // ...
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> rList = new LinkedList<>();
        Queue<TreeNode> aQueue = new LinkedList<>();
        aQueue.offer(root);
        while (!aQueue.isEmpty()) {
            int size = aQueue.size();
            List<Integer> aList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = aQueue.poll();
                aList.add(currentNode.val);
                if (currentNode.left != null) {
                    aQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    aQueue.offer(currentNode.right);
                }
            }
            rList.add(aList);
        }
        Collections.reverse(rList);
        return rList;
    }
}
/* EOF */