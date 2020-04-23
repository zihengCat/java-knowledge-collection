package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 954. Array of Doubled Pairs
 * https://leetcode.com/problems/array-of-doubled-pairs/
 */
public class ArrayOfDoubledPairs {
    public boolean canReorderDoubled(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        Map<Integer, Integer> aMap = new HashMap<>();
        for (int n : arr) {
            aMap.put(n, 1 + aMap.getOrDefault(n, 0));
        }
        for (int i = 0; i <= arr.length / 2; i++) {
            int num = arr[2 * i + 1];
        }
        // ...
    }
}
/* EOF */