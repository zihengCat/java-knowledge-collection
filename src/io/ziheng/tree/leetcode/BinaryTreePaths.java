package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 257. Binary Tree Paths
 * https://leetcode.com/problems/binary-tree-paths/
 */
public class BinaryTreePaths {
    private List<String> resultList = new LinkedList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return resultList;
        }
        binaryTreePaths0(root, "");
        return resultList;
    }
    private void binaryTreePaths0(TreeNode root, String result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result += root.val;
            resultList.add(result);
        }
        result += root.val + "->";
        binaryTreePaths0(root.left, result);
        binaryTreePaths0(root.right, result);
    }
}
/* EOF */