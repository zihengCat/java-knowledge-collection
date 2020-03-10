package io.ziheng.recursion.backtracking.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 78. Subsets
 * https://leetcode.com/problems/subsets/
 */
public class Subsets {
    private List<List<Integer>> resultList = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return resultList;
        }
        subsets0(0, nums.length, nums, new LinkedList<Integer>());
        return resultList;
    }
    private void subsets0(int index, int length, int[] nums, List<Integer> list) {
        if (index >= length) {
            resultList.add(new LinkedList<>(list));
            return;
        }
        subsets0(index + 1, length, nums, list);
        list.add(nums[index]);
        subsets0(index + 1, length, nums, list);
        // 状态恢复
        list.remove(list.size() - 1);
    }
}
/* EOF */