package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 383. Ransom Note
 * https://leetcode.com/problems/ransom-note/
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null) {
            return false;
        }
        return canConstructHashing(ransomNote, magazine);
    }
    public boolean canConstructHashing(String ransomNote, String magazine) {
        Map<Character, Integer> aMap = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            aMap.put(c, 1 + aMap.getOrDefault(c, 0));
        }
        for (char c : ransomNote.toCharArray()) {
            if (!aMap.containsKey(c) || aMap.get(c) <= 0) {
                return false;
            }
            aMap.put(c, aMap.get(c) - 1);
        }
        return true;
    }
}
/* EOF */