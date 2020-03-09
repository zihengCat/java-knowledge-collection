package io.ziheng.hashtable.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * LeetCode 49. Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = new String[]{
            "eat","tea","tan","ate","nat","bat",
        };
        System.out.println(
            new GroupAnagrams().groupAnagrams(strs)
        );
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new LinkedList<>();
        }
        return groupAnagramsHashWay(strs);
    }
    private List<List<String>> groupAnagramsHashWay(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] sCharArry = s.toCharArray();
            Arrays.sort(sCharArry);
            String orderedKey = String.valueOf(sCharArry);
            if (!map.containsKey(orderedKey)) {
                List<String> resultList = new LinkedList<>();
                resultList.add(s);
                map.put(orderedKey, resultList);
            } else {
                map.get(orderedKey).add(s);
            }
        }
        return map.values().stream().collect(Collectors.toList());
    }
}
/* EOF */