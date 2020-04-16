package io.ziheng.hashtable.leetcode;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * LeetCode 884. Uncommon Words from Two Sentences
 * https://leetcode.com/problems/uncommon-words-from-two-sentences/
 */
public class UncommonWordsFromTwoSentences {
    public String[] uncommonFromSentences(String A, String B) {
        List<String> resultList = new LinkedList<>();
        Map<String, Integer> aMap = new HashMap<>();
        for (String s : (A + " " + B).split(" ")) {
            aMap.put(s, 1 + aMap.getOrDefault(s, 0));
        }
        for (String key : aMap.keySet()) {
            if (aMap.get(key).intValue() == 1) {
                resultList.add(key);
            }
        }
        return resultList.toArray(new String[resultList.size()]);
    }
}
/* EOF */