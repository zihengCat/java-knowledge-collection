package io.ziheng.search.leetcode;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 140. Word Break II
 * https://leetcode.com/problems/word-break-ii/
 */
public class WordBreakII {
    public static void main(String[] args) {
        // ...
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        return wordBreakDFS(s, wordDict, new HashMap<String, List<String>>());
    }
    private List<String> wordBreakDFS(
        String s, List<String> wordDict,
        Map<String, List<String>> memoMap) {
        if (memoMap.containsKey(s)) {
            return memoMap.get(s);
        }
        List<String> rList = new LinkedList<>();
        if (s.length() == 0) {
            rList.add("");
            return rList;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> subList = wordBreakDFS(
                    s.substring(word.length()), wordDict, memoMap
                );
                for (String str : subList) {
                    rList.add(word + (str.isEmpty() ? "" : " ") + str);
                }
            }
        }
        memoMap.put(s, rList);
        return rList;
    }
}
/* EOF */