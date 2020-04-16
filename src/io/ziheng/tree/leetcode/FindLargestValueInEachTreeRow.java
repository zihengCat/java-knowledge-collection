package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.Queue;
import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 515. Find Largest Value in Each Tree Row
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 */
public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<Integer> resultList = new LinkedList<>();
        Queue<TreeNode> aQueue = new LinkedList<>();
        aQueue.offer(root);
        while (!aQueue.isEmpty()) {
            int size = aQueue.size();
            int maximumValue = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = aQueue.poll();
                maximumValue = Math.max(maximumValue, node.val);
                if (node.left != null) {
                    aQueue.offer(node.left);
                }
                if (node.right != null) {
                    aQueue.offer(node.right);
                }
            }
            resultList.add(Integer.valueOf(maximumValue));
        }
        return resultList;
    }
}
/* EOF */