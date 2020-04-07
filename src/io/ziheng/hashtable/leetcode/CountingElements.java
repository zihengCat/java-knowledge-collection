package io.ziheng.hashtable.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode ???. Counting Elements
 */
public class CountingElements {
    public int countElements(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int cnt = 0;
        Set<Integer> aSet = new HashSet<>();
        for (int n : arr) {
            aSet.add(n);
        }
        for (int n : arr) {
            if (aSet.contains(n + 1)) {
                cnt++;
            }
        }
        return cnt;
    }
}
/* EOF */