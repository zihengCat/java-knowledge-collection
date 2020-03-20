package io.ziheng.string.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 
 */
public class FindAllAnagramsInAString {
    List<Integer> resultList = new LinkedList<>();
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null) {
            return resultList;
        }
        Map<Character, Integer> pMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            if (!pMap.containsKey(c)) {
                pMap.put(c, 1);
            } else {
                pMap.put(c, 1 + pMap.get(c));
            }
        }
        char[] sCharArray = s.toCharArray();
        int windowStart = 0;
        int windowEnd = p.length() - 1;
        while (windowEnd < s.length()) {
            if (pMap.containsKey(s.charAt(windowStart))) {
                if (pMap.equals(buildCharMap(sCharArray, windowStart, windowEnd))) {
                    resultList.add(windowStart);
                }
            }
            windowStart++;
            windowEnd++;
        }
        return resultList;
    }
    private Map<Character, Integer> buildCharMap(char[] arr, int start, int end) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = start; i <= end; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], 1 + map.get(arr[i]));
            }
        }
        return map;
    }
}
/* EOF */