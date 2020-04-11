package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 599. Minimum Index Sum of Two Lists
 * https://leetcode.com/problems/minimum-index-sum-of-two-lists/
 */
public class MinimumIndexSumOfTwoLists {
    /**
     * Time Complexity: O(n + m)
     * Space Complexity: O(n)
     *
     * @param list1
     * @param list2
     * @return String[]
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1 == null
            || list1.length == 0
            || list2 == null
            || list2.length == 0) {
            return new String[0];
        }
        List<String> resultList = new LinkedList<>();
        int minIndexSum = Integer.MAX_VALUE;
        Map<String, Integer> aMap = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            aMap.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            if (aMap.containsKey(list2[i])) {
                int j = aMap.get(list2[i]);
                if (i + j < minIndexSum) {
                    resultList.clear();
                    resultList.add(list2[i]);
                    minIndexSum = i + j;
                } else if (i + j == minIndexSum) {
                    resultList.add(list2[i]);
                }
            }
        }
        return resultList.toArray(new String[resultList.size()]);
    }
}
/* EOF */