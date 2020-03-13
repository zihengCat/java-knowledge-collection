package io.ziheng.hashtable.leetcode;

import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * LeetCode 15. 3Sum
 * https://leetcode.com/problems/3sum/
 */
public class ThreeSum {
    private List<List<Integer>> resultList = new LinkedList<>();
    private Set<List<Integer>> resultSet = new HashSet<>();
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new LinkedList<>();
        }
        threeSum0(nums);
        return resultSet.stream().collect(Collectors.toList());
    }
    private void threeSum0(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> aSet = new HashSet<>();
        for (int num : nums) {
            aSet.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int complementNum = -1 * (nums[i] + nums[j]);
                if (aSet.contains(complementNum)) {
                    List<Integer> aList = new LinkedList<>();
                    aList.add(nums[i]);
                    aList.add(nums[j]);
                    aList.add(complementNum);
                    Collections.sort(aList);
                    resultSet.add(aList);
                }
            }
        }
    }
}
/* EOF */