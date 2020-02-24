package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 938. Range Sum of BST
 * https://leetcode.com/problems/range-sum-of-bst/
 */
public class RangeSumOfBST {
    public int rangeSumBST(TreeNode root, int L, int R) {
        //return rangeSumBSTRecursively(root, L, R, 0);
        return rangeSumBSTIteratively(root, L, R);
    }
    private int rangeSumBSTIteratively(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        int rangeSum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val >= L && node.val <= R) {
                rangeSum += node.val;
            }
            if (node.val > L && node.left != null) {
                queue.offer(node.left);
            }
            if (node.val < R && node.right != null) {
                queue.offer(node.right);
            }
        }
        return rangeSum;
    }
    private int rangeSumBSTRecursively(TreeNode root, int L, int R, int sum) {
        if (root == null) {
            return sum;
        }
        if (root.val >= L && root.val <= R) {
            sum += root.val;
        }
        if (root.val > L) {
            sum = rangeSumBSTRecursively(root.left, L, R, sum);
        }
        if (root.val < R) {
            sum = rangeSumBSTRecursively(root.right, L, R, sum);
        }
        return sum;
    }
}
/* EOF */