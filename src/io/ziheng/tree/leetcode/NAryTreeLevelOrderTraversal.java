package io.ziheng.tree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 429. N-ary Tree Level Order Traversal
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 */
public class NAryTreeLevelOrderTraversal {
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
    }
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> resultList = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node currentNode = queue.poll();
                list.add(currentNode.val);
                for (Node node : currentNode.children) {
                    queue.offer(node);
                }
            }
            resultList.add(list);
        }
        return resultList;
    }
}
/* EOF */