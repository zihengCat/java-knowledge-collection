package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 91. Decode Ways
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {
    public static void main(String[] args) {
        new DecodeWays().numDecodings("123");
    }
    private Map<Character, Integer> map;
    private int cnt;
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        map = new HashMap<>();
        cnt = 0;
        for (int i = 1; i <= 26; i++) {
            map.put((char)(64 + i), i);
        }
        // numDecodingsRecursively(s);
        return cnt;
    }
    private int numDecodingsRecursively(int i, int n, String s) {
        if (i >= n) {
            return 1;
        }
        return 0;
    }
}
/* EOF */