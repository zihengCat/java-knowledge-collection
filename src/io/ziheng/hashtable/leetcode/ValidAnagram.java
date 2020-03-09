package io.ziheng.hashtable.leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 242. Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        // return isAnagramSortWay(s, t);
        return isAnagramHashWay(s, t);
    }
    public boolean isAnagramHashWay(String s, String t) {
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c: sCharArray) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, 1 + map.get(c));
            }
        }
        for (char c: tCharArray) {
            if (!map.containsKey(c)) {
                return false;
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            if (entry.getValue() > 0) {
                return false;
            }
        }
        return true;
    }
    private boolean isAnagramSortWay(String s, String t) {
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        Arrays.sort(sCharArray);
        Arrays.sort(tCharArray);
        for (int i = 0; i < sCharArray.length; i++) {
            if (sCharArray[i] != tCharArray[i]) {
                return false;
            }
        }
        return true;
    }
}
/* EOF */