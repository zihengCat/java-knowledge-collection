package io.ziheng.recursion.backtracking.leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

public class PermutationsII {
    private List<List<Integer>> resultList = new LinkedList<>();
    private Set<List<Integer>> resultSet = new HashSet<>();
    private boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return resultList;
        }
        this.used = new boolean[nums.length];
        List<Integer> result = new ArrayList<>();
        permuteUnique0(nums, 0, result);
        return resultSet.stream().collect(Collectors.toList());
    }
    private void permuteUnique0(int[] nums, int index, List<Integer> result) {
        if (index == nums.length) {
            resultSet.add(new LinkedList<>(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                result.add(nums[i]);
                used[i] = true;
                permuteUnique0(nums, index + 1, result);
                result.remove(result.size() - 1);
                used[i] = false;
            }
        }
    }
}
/* EOF */