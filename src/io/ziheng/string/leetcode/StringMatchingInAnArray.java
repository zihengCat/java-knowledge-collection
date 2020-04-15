package io.ziheng.string.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 1408. String Matching in an Array
 * https://leetcode.com/problems/string-matching-in-an-array/
 */
public class StringMatchingInAnArray {
    public List<String> stringMatching(String[] words) {
        if (words == null || words.length == 0) {
            return new LinkedList<>();
        }
        return stringMatchingBruteForce(words);
    }
    public List<String> stringMatchingBruteForce(String[] words) {
        List<String> resultList = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (!words[i].equals(words[j])
                    && words[i].indexOf(words[j]) != -1
                    && !resultList.contains(words[j])) {
                    resultList.add(words[j]);
                }
            }
        }
        return resultList;
    }
}
/* EOF */