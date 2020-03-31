package io.ziheng.hashtable.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 3. Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int cnt = 0;
        int strLength = s.length();
        int windowStart = 0;
        int windowEnd = 0;
        Set<Character> aSet = new HashSet<>();
        while (windowStart < strLength && windowEnd < strLength) {
            // try extends [i, j]
            if (!aSet.contains(s.charAt(windowEnd))) {
                aSet.add(s.charAt(windowEnd));
                windowEnd++;
                cnt = Math.max(cnt, windowEnd - windowStart);
            } else {
                aSet.remove(s.charAt(windowStart));
                windowStart++;
            }
        }
        return cnt;
    }
}
/* EOF */