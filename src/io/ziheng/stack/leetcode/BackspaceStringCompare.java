package io.ziheng.stack.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 844. Backspace String Compare
 * https://leetcode.com/problems/backspace-string-compare/
 */
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        String convertedS = convert(S);
        String convertedT = convert(T);
        return convertedS.equals(convertedT);
    }
    private String convert(String s) {
        char[] arr = s.toCharArray();
        Deque<Character> aStack = new LinkedList<>();
        for (char c : arr) {
            if (c == '#') {
                if (!aStack.isEmpty()) {
                    aStack.pop();
                }
            } else {
                aStack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!aStack.isEmpty()) {
            sb.append(aStack.pop());
        }
        return sb.reverse().toString();
    }
}
/* EOF */