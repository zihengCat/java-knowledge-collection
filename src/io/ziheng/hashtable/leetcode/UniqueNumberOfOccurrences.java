package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

/**
 * LeetCode 1207. Unique Number of Occurrences
 * https://leetcode.com/problems/unique-number-of-occurrences/
 */
public class UniqueNumberOfOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        Map<Integer, Integer> aMap = new HashMap<>();
        for (int n : arr) {
            if (aMap.containsKey(n)) {
                aMap.put(n, 1 + aMap.get(n));
            } else {
                aMap.put(n, 1);
            }
        }
        return aMap.values().size()
            == new HashSet<Integer>(aMap.values()).size();
    }
}
/* EOF */