package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 144. Binary Tree Preorder Traversal
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 */
public class BinaryTreePreorderTraversal {
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
     * 前序遍历二叉树（模拟系统栈）。
     *
     * @param root
     * @return List<Integer>
     */
    public List<Integer> preorderTraversalV3(TreeNode root) {
        List<Integer> aList = new LinkedList<>();
        if (root == null) {
            return aList;
        }
        Deque<Command> aStack = new LinkedList<>();
        aStack.push(new Command(CMD.GO, root));
        while (!aStack.isEmpty()) {
            Command command = aStack.pop();
            if (command.cmd == CMD.VISIT) {
                // Do visitTreeNode() here.
                aList.add(command.node.val);
            } else if (command.cmd == CMD.GO) {
                // Preorder: Root, Left, Right
                if (command.node.right != null) {
                    aStack.push(new Command(CMD.GO, command.node.right));
                }
                if (command.node.left != null) {
                    aStack.push(new Command(CMD.GO, command.node.left));
                }
                aStack.push(new Command(CMD.VISIT, command.node));
            }
        }
        return aList;
    }
    /**
     * 前序遍历二叉树（迭代）。
     *
     * @param root
     * @return List<Integer>
     */
    public List<Integer> preorderTraversalV2(TreeNode root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }
        Deque<TreeNode> aStack = new LinkedList<>();
        List<Integer> aList = new LinkedList<>();
        aStack.push(root);
        while (!aStack.isEmpty()) {
            TreeNode node = aStack.pop();
            aList.add(node.val);
            if (node.right != null) {
                aStack.push(node.right);
            }
            if (node.left != null) {
                aStack.push(node.left);
            }
        }
        return aList;
    }
    private List<Integer> list = new LinkedList<>();
    /**
     * 前序遍历二叉树（递归）。
     *
     * @param root
     * @return List<Integer>
     */
    public List<Integer> preorderTraversalV1(TreeNode root) {
        preorderTraversal0(root);
        return list;
    }
    private void preorderTraversal0(TreeNode root) {
        if (root == null) {
            return;
        }
        visitNode(root);
        preorderTraversal0(root.left);
        preorderTraversal0(root.right);
    }
    private void visitNode(TreeNode node) {
        if (node != null) {
            this.list.add(node.val);
        }
    }
}
/* EOF */