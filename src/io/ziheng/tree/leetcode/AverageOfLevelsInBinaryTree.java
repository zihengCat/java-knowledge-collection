package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * LeetCode 637. Average of Levels in Binary Tree
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 */
public class AverageOfLevelsInBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<Double> resultList = new LinkedList<>();
        Queue<TreeNode> aQueue = new LinkedList<>();
        aQueue.offer(root);
        while (!aQueue.isEmpty()) {
            int size = aQueue.size();
            double currentSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = aQueue.poll();
                currentSum += node.val;
                if (node.left != null) {
                    aQueue.offer(node.left);
                }
                if (node.right != null) {
                    aQueue.offer(node.right);
                }
            }
            resultList.add(Double.valueOf(currentSum / size));
        }
        return resultList;
    }
}
/* EOF */