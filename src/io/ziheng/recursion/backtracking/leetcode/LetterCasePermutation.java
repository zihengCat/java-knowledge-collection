package io.ziheng.recursion.backtracking.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 784. Letter Case Permutation
 * https://leetcode.com/problems/letter-case-permutation/
 */
public class LetterCasePermutation {
    private List<String> resultList = new LinkedList<>();
    public List<String> letterCasePermutation(String s) {
        if (s == null || s.length() == 0) {
            return new LinkedList<>();
        }
        letterCasePermutation0(s, 0, s.length(), "");
        return resultList;
    }
    private void letterCasePermutation0(
        String s, int currentIndex, int maximumLength, String res) {
        if (currentIndex >= maximumLength) {
            resultList.add(res);
            return;
        }
        char c = s.charAt(currentIndex);
        if (Character.isDigit(c)) {
            letterCasePermutation0(s, currentIndex + 1, maximumLength, res + c);
        } else {
            letterCasePermutation0(s, currentIndex + 1, maximumLength,
                res + Character.toLowerCase(c)
            );
            letterCasePermutation0(s, currentIndex + 1, maximumLength,
                res + Character.toUpperCase(c)
            );
        }
    }
}
/* EOF */