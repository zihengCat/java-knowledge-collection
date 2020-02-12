package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 145. Binary Tree Postorder Traversal
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class BinaryTreePostorderTraversal {
    private static enum CMD {
        GO,
        VISIT,
    }
    private class Command {
        public CMD cmd;
        public TreeNode node;
        public Command(CMD cmd, TreeNode node) {
            this.cmd = cmd;
            this.node = node;
        }
    }
    /**
     * 后序遍历二叉树（迭代->模拟系统栈调用）。
     *
     * @param root
     * @return List<Integer>
     */
    public List<Integer> postorderTraversalV2(TreeNode root) {
        List<Integer> resultList = new LinkedList<>();
        if (root == null) {
            return resultList;
        }
        Deque<Command> stack = new LinkedList<>();
        stack.push(new Command(CMD.GO, root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();
            if (command.cmd == CMD.VISIT) {
                // visitTreeNode()
                resultList.add(command.node.val);
            } else if (command.cmd == CMD.GO) {
                // Postorder: Left, Right, Root
                stack.push(new Command(CMD.VISIT, command.node));
                if (command.node.right != null) {
                    stack.push(new Command(CMD.GO, command.node.right));
                }
                if (command.node.left != null) {
                    stack.push(new Command(CMD.GO, command.node.left));
                }
            }
        }
        return resultList;
    }
    private List<Integer> list = new LinkedList<>();
    /**
     * 后序遍历二叉树（递归）。
     *
     * @param root
     * @return List<Integer>
     */
    public List<Integer> postorderTraversalV1(TreeNode root) {
        postorderTraversal0(root);
        return list;
    }
    private void postorderTraversal0(TreeNode node) {
        if (node == null) {
            return;
        }
        postorderTraversal0(node.left);
        postorderTraversal0(node.right);
        visitNode(node);
    }
    private void visitNode(TreeNode node) {
        if (node != null) {
            this.list.add(node.val);
        }
    }
}
/* EOF */