package io.ziheng.hashtable.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new LinkedList<>();
        }
        List<List<String>> resultList = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String orderedKey = String.valueOf(charArray);
            if (map.containsKey(orderedKey)) {
                map.get(orderedKey).add(str);
            } else {
                List<String> list = new LinkedList<>();
                list.add(str);
                map.put(orderedKey, list);
            }
        }
        for (List<String> list : map.values()) {
            resultList.add(list);
        }
        return resultList;
    }
}
/* EOF */