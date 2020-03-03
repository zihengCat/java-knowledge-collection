package io.ziheng.recursion.backtracking.leetcode;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 46. Permutations
 * https://leetcode.com/problems/permutations/
 */
public class Permutations {
    public static void main(String[] args) {
        List<Integer> listA = new LinkedList<>();
        List<Integer> listB = new ArrayList<>();
        listA.add(1);
        listB.add(1);
        Set<List<Integer>> set = new HashSet<>();
        set.add(listA);
        set.add(listB);
        System.out.println(set);
    }
    private List<List<Integer>> resultList = new LinkedList<>();
    private boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0 ) {
            return resultList;
        }
        this.used = new boolean[nums.length];
        LinkedList<Integer> result = new LinkedList<>();
        permute1(nums, 0, result);
        return resultList;
    }
    private void permute1(int[] nums, int index, LinkedList<Integer> result) {
        if (index == nums.length) {
            resultList.add(new LinkedList<>(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                result.push(nums[i]);
                used[i] = true;
                permute1(nums, index + 1, result);
                result.pop();
                used[i] = false;
            }
        }
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