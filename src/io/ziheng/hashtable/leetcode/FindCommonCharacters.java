package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 1002. Find Common Characters
 * https://leetcode.com/problems/find-common-characters/
 */
public class FindCommonCharacters {
    public List<String> commonChars(String[] arr) {
        if (arr == null || arr.length == 0) {
            return new LinkedList<>();
        }
        Map<Character, Integer> aMap = buildDictMap(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            aMap = findCommon(aMap, buildDictMap(arr[i]));
        }
        return mapToList(aMap);
    }
    private List<String> mapToList(Map<Character, Integer> aMap) {
        List<String> aList = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : aMap.entrySet()) {
            String s = String.valueOf(entry.getKey().charValue());
            int size = entry.getValue().intValue();
            for (int i = 0; i < size; i++) {
                aList.add(s);
            }
        }
        return aList;
    }
    private Map<Character, Integer> findCommon(
        Map<Character, Integer> mapA,
        Map<Character, Integer> mapB) {
        if (mapB.size() > mapA.size()) {
            return findCommon(mapB, mapA);
        }
        Map<Character, Integer> aMap = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : mapA.entrySet()) {
            Character c = entry.getKey();
            if (mapB.containsKey(c)) {
                aMap.put(c, Math.min(mapA.get(c), mapB.get(c)));
            }
        }
        return aMap;
    }
    private Map<Character, Integer> buildDictMap(String s) {
        Map<Character, Integer> aMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            aMap.put(c, 1 + aMap.getOrDefault(c, 0));
        }
        return aMap;
    }
}
/* EOF */