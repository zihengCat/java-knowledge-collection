package io.ziheng.hashtable.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1399. Count Largest Group
 * https://leetcode.com/problems/count-largest-group/
 */
public class CountLargestGroup {
    public static void main(String[] args) {
        CountLargestGroup obj = new CountLargestGroup();
        int n = 13;
        System.out.println(
            obj.countLargestGroup(n)
        );
    }
    public int countLargestGroup(int n) {
        if (n < 0) {
            return 0;
        }
        Map<Integer, Integer> aMap = new HashMap<>();
        int maximumCount = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int digitsSum = getDigitsSum(i);
            if (aMap.containsKey(digitsSum)) {
                aMap.put(digitsSum, 1 + aMap.get(digitsSum));
            } else {
                aMap.put(digitsSum, 1);
            }
            maximumCount = Math.max(maximumCount, aMap.get(digitsSum));
        }
        int cnt = 0;
        for (int num : aMap.values()) {
            if (num == maximumCount) {
                cnt++;
            }
        }
        return cnt;
    }
    private int getDigitsSum(int n) {
        int digitsSum = 0;
        while (n > 0) {
            digitsSum += n % 10;
            n /= 10;
        }
        return digitsSum;
    }
}
/* EOF */