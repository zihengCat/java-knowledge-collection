package io.ziheng.recursion.backtrcking.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        List<String> result = new LetterCombinationsOfAPhoneNumber().letterCombinations("234");
        System.out.println(result);
    }
    private List<String> resultList = new LinkedList<>();
    /**
     * 号码与字母映射。
     */
    private static final Map<Character, String> lettersMap = new HashMap<>();
    static {
        lettersMap.put('0', " ");    // 0
        lettersMap.put('1', "");     // 1
        lettersMap.put('2', "abc");  // 2
        lettersMap.put('3', "def");  // 3
        lettersMap.put('4', "ghi");  // 4
        lettersMap.put('5', "jkl");  // 5
        lettersMap.put('6', "mno");  // 6
        lettersMap.put('7', "pqrs"); // 7
        lettersMap.put('8', "tuv");  // 8
        lettersMap.put('9', "wxyz"); // 9
    }
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return resultList;
        }
        findCombination(digits, 0, "");
        return resultList;
    }
    /**
     * ...
     * @param digis 原始字符串
     * @param index 索引
     * @param s {@code digits[0... index - 1]}的解
     * @return void
     */
    private void findCombination(String digits, int index, String s) {
        System.out.printf("%d: %s\n", index, s);
        if (index == digits.length()) {
            resultList.add(s);
            return;
        }
        char c = digits.charAt(index);
        assert c >= '0' && c <= '9' && c != '1';
        String letters = lettersMap.get(c);
        for (int i = 0; i < letters.length(); i++) {
            findCombination(
                digits,
                index + 1,
                s + letters.charAt(i)
            );
        }
    }
}
/* EOF */