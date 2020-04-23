package io.ziheng.recursion.backtracking.leetcode;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * LeetCode 90. Subsets II
 * https://leetcode.com/problems/subsets-ii/
 */
public class SubsetsII {
    public static void main(String[] args) {
        SubsetsII obj = new SubsetsII();
        int[] nums = new int[]{
            1, 2, 2,
        };
        System.out.println(
            obj.subsetsWithDup(nums)
        );
    }
    private Set<List<Integer>> resultSet = new HashSet<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new LinkedList<>();
        }
        subsetsWithDup0(0, nums.length, new LinkedList<>(), nums);
        return resultSet.stream().collect(Collectors.toList());
    }
    private void subsetsWithDup0(
        int currentIndex,
        int maximumLength,
        List<Integer> currentList,
        int[] nums) {
        if (currentIndex >= maximumLength) {
            List<Integer> aList = new LinkedList<>(currentList);
            Collections.sort(aList);
            resultSet.add(aList);
            return;
        }
        subsetsWithDup0(currentIndex + 1, maximumLength, currentList, nums);
        currentList.add(nums[currentIndex]);
        subsetsWithDup0(currentIndex + 1, maximumLength, currentList, nums);
        currentList.remove(currentList.size() - 1);
    }
}
/* EOF */