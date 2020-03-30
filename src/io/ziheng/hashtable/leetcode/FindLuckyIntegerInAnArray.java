package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 1394. Find Lucky Integer in an Array
 * https://leetcode.com/problems/find-lucky-integer-in-an-array/
 */
public class FindLuckyIntegerInAnArray {
    public int findLucky(int[] arr) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int n : arr) {
            if (treeMap.containsKey(n)) {
                treeMap.put(n, 1 + treeMap.get(n));
            } else {
                treeMap.put(n, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : treeMap.descendingMap().entrySet()) {
            if (entry.getKey().equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
/* EOF */