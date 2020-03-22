package io.ziheng.tree.leetcode;

import java.util.List;
import java.util.PriorityQueue;
import java.util.LinkedList;

/**
 * LeetCode 1305. All Elements in Two Binary Search Trees
 * https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 */
public class AllElementsInTwoBinarySearchTrees {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        traversalBinaryTree(root1, priorityQueue);
        traversalBinaryTree(root2, priorityQueue);
        List<Integer> resultList = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            resultList.add(priorityQueue.poll());
        }
        return resultList;
    }
    private void traversalBinaryTree(TreeNode root, PriorityQueue<Integer> queue) {
        if (root == null) {
            return;
        }
        queue.offer(root.val);
        traversalBinaryTree(root.left, queue);
        traversalBinaryTree(root.right, queue);
    }
}
/* EOF */