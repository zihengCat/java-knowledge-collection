package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 103. Binary Tree Zigzag Level Order Traversal
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    private static int IN_ORDER = 1;
    private static int REVERSE_ORDER = -1;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<List<Integer>>();
        }
        List<List<Integer>> resultList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int order = IN_ORDER;
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                if (order == REVERSE_ORDER) {
                    levelList.add(0, currentNode.val);
                } else if (order == IN_ORDER) {
                    levelList.add(currentNode.val);
                }
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            order = order == IN_ORDER ? REVERSE_ORDER : IN_ORDER;
            resultList.add(levelList);
        }
        return resultList;
    }
}
/* EOF */