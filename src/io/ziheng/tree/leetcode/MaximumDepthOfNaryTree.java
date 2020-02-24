package io.ziheng.tree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 559. Maximum Depth of N-ary Tree
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 */
public class MaximumDepthOfNaryTree {
    private class Node {
        public int val;
        public List<Node> children;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    public int maxDepth(Node root) {
        //return maxDepthRecursively(root);
        return maxDepthIteratively(root);
    }
    private int maxDepthIteratively(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        int level = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.children != null) {
                    for (Node n : node.children) {
                        queue.offer(n);
                    }
                }
            }
            level++;
        }
        return level;
    }
    private int maxDepthRecursively(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for (Node n : root.children) {
            max = Math.max(max, maxDepthRecursively(n));
        }
        return max + 1;
    }
}
/* EOF */