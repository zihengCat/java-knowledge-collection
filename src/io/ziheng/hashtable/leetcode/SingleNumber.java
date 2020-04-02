package io.ziheng.hashtable.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 136. Single Number
 * https://leetcode.com/problems/single-number/
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return singleNumberHashWay(nums);
    }
    private int singleNumberHashWay(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, 1);
            } else {
                map.put(n, 1 + map.get(n));
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(1)) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
/* EOF */