package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 111. Minimum Depth of Binary Tree
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        // DFS
        // if (root == null) {
        //     return 0;
        // }
        // minDepthDFS(root, 1);
        // return minDepth;

        // BFS
        // return minDepthBFS(root);

        // Recursion
        return minDepthRecursively(root);
    }
    private int minDepth = Integer.MAX_VALUE;
    /**
     * DFS
     *
     * @param root
     * @param level
     * @return void
     */
    private void minDepthDFS(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (root.left == null
            && root.right == null) {
            minDepth = Math.min(minDepth, level);
        }
        minDepthDFS(root.left, level + 1);
        minDepthDFS(root.right, level + 1);
    }
    /**
     * BFS
     *
     * @param root
     * @return int
     */
    private int minDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int minDepth = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null
                    && node.right== null) {
                    return minDepth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            minDepth++;
        }
        return minDepth;
    }
    /**
     * Recursion
     *
     * @param root
     * @return int
     */
    private int minDepthRecursively(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepthRecursively(root.left);
        int right = minDepthRecursively(root.right);
        return left == 0 || right == 0
            ? 1 + left + right
            : 1 + Math.min(left, right);
    }
}
/* EOF */