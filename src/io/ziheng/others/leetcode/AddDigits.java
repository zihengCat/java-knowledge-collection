package io.ziheng.others.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 258. Add Digits
 * https://leetcode.com/problems/add-digits/
 */
public class AddDigits {
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        int val = num;
        List<Integer> digits = findDigits(val);
        while (digits.size() > 1) {
            int nextVal = 0;
            for (int n : digits) {
                nextVal += n;
            }
            val = nextVal;
            digits = findDigits(val);
        }
        return digits.get(0);
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