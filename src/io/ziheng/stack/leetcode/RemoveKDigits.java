package io.ziheng.stack.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 402. Remove K Digits
 * https://leetcode.com/problems/remove-k-digits/
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        Deque<Character> aStack = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!aStack.isEmpty()
                && k > 0
                && aStack.peek() > c) {
                aStack.pop();
                k--;
            }
            aStack.push(c);
        }
        while (k > 0) {
            aStack.pop();
            k--;
        }
        while (!aStack.isEmpty()
            && aStack.peekLast() == '0') {
            aStack.pollLast();
        }
        StringBuilder sb = new StringBuilder();
        while (!aStack.isEmpty()) {
            sb.append(aStack.pollLast());
        }
        String retStr = sb.toString();
        return retStr.equals("") ? "0" : retStr;
    }
}
/* EOF */