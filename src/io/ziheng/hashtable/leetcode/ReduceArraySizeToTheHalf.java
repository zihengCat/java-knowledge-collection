package io.ziheng.hashtable.leetcode;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * LeetCode 1338. Reduce Array Size to The Half
 * https://leetcode.com/problems/reduce-array-size-to-the-half/
 */
public class ReduceArraySizeToTheHalf {
    public static void main(String[] args) {
        ReduceArraySizeToTheHalf obj = new ReduceArraySizeToTheHalf();
        int[] arr = new int[]{3,3,3,3,5,5,5,2,2,7};
        System.out.println(
            obj.minSetSize(arr)
        );
    }
    public int minSetSize(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> aMap = new HashMap<>();
        for (int n : arr) {
            if (aMap.containsKey(n)) {
                aMap.put(n, 1 + aMap.get(n));
            } else {
                aMap.put(n, 1);
            }
        }
        List<Integer> aList = new LinkedList<>();
        for (Integer n : aMap.values()) {
            aList.add(n);
        }
        Collections.sort(
            aList,
            new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            }
        );
        int arrayLength = arr.length;
        int cnt = 0;
        int sum = 0;
        for (int n : aList) {
            cnt++;
            sum += n;
            if (sum >= arrayLength / 2) {
                break;
            }
        }
        return cnt;
    }
}
/* EOF */