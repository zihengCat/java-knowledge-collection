package io.ziheng.hashtable.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 202. Happy Number
 * https://leetcode.com/problems/happy-number/
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        int val = n;
        Set<Integer> aSet = new HashSet<>();
        while (val != 1) {
            if (aSet.contains(val)) {
                return false;
            } else {
                aSet.add(val);
            }
            int nextVal = 0;
            List<Integer> digits = findDigits(val);
            for (int digit : digits) {
                nextVal += digit * digit;
            }
            val = nextVal;
        }
        return true;
    }
    private List<Integer> findDigits(int n) {
        List<Integer> list = new LinkedList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        return list;
    }
}
/* EOF */