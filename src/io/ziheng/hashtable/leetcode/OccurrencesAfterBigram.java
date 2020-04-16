package io.ziheng.hashtable.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 1078. Occurrences After Bigram
 * https://leetcode.com/problems/occurrences-after-bigram/
 */
public class OccurrencesAfterBigram {
    public String[] findOcurrences(String text, String first, String second) {
        List<String> resultList = new LinkedList<>();
        String[] words = text.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].equals(first)
                && words[i + 1].equals(second)) {
                if (i + 2 < words.length) {
                    resultList.add(words[i + 2]);
                }
            }
        }
        return resultList.toArray(new String[resultList.size()]);
    }
}
/* EOF */