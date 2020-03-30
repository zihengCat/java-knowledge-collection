package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 961. N-Repeated Element in Size 2N Array
 * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
 */
public class NRepeatedElementInSize2NArray {
    public int repeatedNTimes(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        Map<Integer, Integer> aMap = new HashMap<>();
        for (int n : A) {
            if (aMap.containsKey(n)) {
                aMap.put(n, 1 + aMap.get(n));
            } else {
                aMap.put(n, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : aMap.entrySet()) {
            if (entry.getValue() > 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
/* EOF */