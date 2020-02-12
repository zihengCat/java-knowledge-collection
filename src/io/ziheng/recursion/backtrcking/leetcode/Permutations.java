package io.ziheng.recursion.backtrcking.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 46. Permutations
 * https://leetcode.com/problems/permutations/
 */
public class Permutations {
    private List<List<Integer>> resultList = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0 ) {
            return resultList;
        }
        LinkedList<Integer> list = new LinkedList<>();
        permute0(nums, 0, list);
        return resultList;
    }
    private void permute0(int[] nums, int index, LinkedList<Integer> result) {
        if (index == nums.length) {
            List<Integer> list = new LinkedList<>(result);
            resultList.add(list);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!result.contains(nums[i])) {
                result.push(nums[i]);
                permute0(nums, index + 1, result);
                result.pop();
            }
        }
    }
}
/* EOF */