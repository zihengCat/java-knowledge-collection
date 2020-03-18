package io.ziheng.hashtable.leetcode;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 229. Majority Element II
 * https://leetcode.com/problems/majority-element-ii/
 */
public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new LinkedList<>();
        }
        List<Integer> resultList = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, 1 + map.get(num));
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 3) {
                resultList.add(entry.getKey());
            }
        }
        return resultList;
    }
}
/* EOF */