package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 1302. Deepest Leaves Sum
 * https://leetcode.com/problems/deepest-leaves-sum/
 */
public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // In each level -> reset sum
            sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return sum;
    }
}
/* EOF */