package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 94. Binary Tree Inorder Traversal
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 */
public class BinaryTreeInorderTraversal {
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
     * 中序遍历二叉树（迭代->模拟系统栈调用）。
     *
     * @param root
     * @return List<Integer>
     */
    public List<Integer> inorderTraversalV2(TreeNode root) {
        List<Integer> resultList = new LinkedList<>();
        if (root == null) {
            return resultList;
        }
        Deque<Command> stack = new LinkedList<>();
        stack.push(new Command(CMD.GO, root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();
            if (command.cmd == CMD.VISIT) {
                // Do visitTreeNode() here
                resultList.add(command.node.val);
            } else if (command.cmd == CMD.GO) {
                // Inorder: Left, Root, Right
                if (command.node.right != null) {
                    stack.push(new Command(CMD.GO, command.node.right));
                }
                stack.push(new Command(CMD.VISIT, command.node));
                if (command.node.left != null) {
                    stack.push(new Command(CMD.GO, command.node.left));
                }
           }
        }
        return resultList;
    }
    private List<Integer> list = new LinkedList<>();
    /**
     * 中序遍历二叉树（递归）。
     *
     * @param root
     * @return List<Integer>
     */
    public List<Integer> inorderTraversalV1(TreeNode root) {
        inorderTraversal0(root);
        return list;
    }
    private void inorderTraversal0(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversal0(node.left);
        visitNode(node);
        inorderTraversal0(node.right);
    }
    private void visitNode(TreeNode node) {
        if (node != null) {
            this.list.add(node.val);
        }
    }
}
/* EOF */