package io.ziheng.tree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
 * LeetCode 103. Binary Tree Zigzag Level Order Traversal
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        // ...
    }
    private static int IN_ORDER = 1;
    private static int REVERSE_ORDER = -1;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> rList = new LinkedList<>();
        Queue<TreeNode> aQueue = new LinkedList<>();
        int order = IN_ORDER;
        aQueue.offer(root);
        while (!aQueue.isEmpty()) {
            List<Integer> aList = new LinkedList<>();
            int size = aQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = aQueue.poll();
                if (order == IN_ORDER) {
                    aList.add(currentNode.val);
                } else if (order == REVERSE_ORDER) {
                    aList.add(0, currentNode.val);
                }
                if (currentNode.left != null) {
                    aQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    aQueue.offer(currentNode.right);
                }
            }
            rList.add(aList);
            order = order == IN_ORDER ? REVERSE_ORDER : IN_ORDER;
        }
        return rList;
    }
}
/* EOF */