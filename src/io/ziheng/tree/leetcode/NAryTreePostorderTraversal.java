package io.ziheng.tree.leetcode;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
/**
 * LeetCode 590. N-ary Tree Postorder Traversal
 * https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 */
public class NAryTreePostorderTraversal {
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
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }
        List<Integer> resultList = new LinkedList<>();
        //postorderRecursively(root, resultList);
        postorderIteratively(root, resultList);
        return resultList;
    }
    // Postorder: Left, Right, Root
    private void postorderRecursively(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        for (Node node : root.children) {
            postorderRecursively(node, list);
        }
        list.add(root.val);
    }
    private void postorderIteratively(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            list.add(currentNode.val);
            for (int i = 0; i < currentNode.children.size(); i++) {
                stack.push(currentNode.children.get(i));
            }
        }
        Collections.reverse(list);
    }
}
/* EOF */