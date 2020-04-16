package io.ziheng.hashtable.leetcode;

import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 645. Set Mismatch
 * https://leetcode.com/problems/set-mismatch/
 */
public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] resultArray = new int[2];
        int originalSum = (1 + nums.length) * nums.length / 2;
        Set<Integer> aSet = new HashSet<>();
        for (int num : nums) {
            if (aSet.contains(num)) {
                resultArray[0] = num;
            }
            aSet.add(num);
        }
        int currentSum = aSet.stream().mapToInt(s -> s.intValue()).sum();
        resultArray[1] = originalSum - currentSum;
        return resultArray;
    }
}
/* EOF */