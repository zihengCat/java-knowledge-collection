package io.ziheng.hashtable.leetcode;

import java.util.TreeMap;

/**
 * LeetCode 1122. Relative Sort Array
 * https://leetcode.com/problems/relative-sort-array/
 */
public class RelativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] resultArray = new int[arr1.length];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int n : arr1) {
            treeMap.put(n, 1 + treeMap.getOrDefault(n, 0));
        }
        int index = 0;
        for (int n : arr2) {
            if (treeMap.containsKey(n)
                && treeMap.get(n).intValue() > 0) {
                int size = treeMap.get(n);
                for (int i = 0; i < size; i++) {
                    resultArray[index] = n;
                    index++;
                }
                treeMap.remove(n);
            }
        }
        index = resultArray.length - 1;
        for (int num : treeMap.descendingKeySet()) {
            int size = treeMap.get(num);
            for (int i = 0; i < size; i++) {
                resultArray[index] = num;
                index--;
            }
        }
        return resultArray;
    }
}
/* EOF */