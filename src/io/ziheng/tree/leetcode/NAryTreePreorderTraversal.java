package io.ziheng.tree.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode. 589. N-ary Tree Preorder Traversal
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 */
public class NAryTreePreorderTraversal {
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
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }
        List<Integer> resultList = new LinkedList<>();
        preorderRecursively(root, resultList);
        preorderIteratively(root, resultList);
        return resultList;
    }
    private void preorderIteratively(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            list.add(currentNode.val);
            // 反向压入栈
            for (int i = currentNode.children.size() - 1; i >= 0; i--) {
                stack.push(currentNode.children.get(i));
            }
        }
    }
    // Preorder -> Root, Left, Right
    private void preorderRecursively(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        for (Node node : root.children) {
            preorderRecursively(node, list);
        }
    }
}
/* EOF */