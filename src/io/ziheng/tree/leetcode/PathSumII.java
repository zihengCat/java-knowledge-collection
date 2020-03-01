package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 113. Path Sum II
 * https://leetcode.com/problems/path-sum-ii/
 */
public class PathSumII {
    private List<List<Integer>> resultList = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        pathSumRecursively(root, sum, new LinkedList<Integer>());
        return resultList;
    }
    private void pathSumRecursively(TreeNode root, int sum, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                this.resultList.add(new LinkedList<>(list));
                list.remove(list.size() - 1);
                return;
            }
        } else {
            pathSumRecursively(root.left, sum - root.val, list);
            pathSumRecursively(root.right, sum - root.val, list);
        }
        list.remove(list.size() - 1);
    }
}
/* EOF */