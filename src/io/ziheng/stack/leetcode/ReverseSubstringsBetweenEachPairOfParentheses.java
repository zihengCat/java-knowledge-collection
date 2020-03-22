package io.ziheng.stack.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 1190. Reverse Substrings Between Each Pair of Parentheses
 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {
    /**
     * 主函数 -> 测试用例
     */
    public static void main(String[] args) {
        ReverseSubstringsBetweenEachPairOfParentheses obj =
        new ReverseSubstringsBetweenEachPairOfParentheses();
        String s = "(ed(et(oc))el)";
        System.out.println(
            obj.reverseParentheses(s)
        );
    }
    public String reverseParentheses(String s) {
        Deque<Character> aStack = new LinkedList<>();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c == '(') {
                aStack.push(c);
            } else if (c == ')') {
                StringBuilder sb = new StringBuilder();
                char top = 0;
                while ((top = aStack.pop()) != '(') {
                    sb.append(top);
                }
                char[] reversedCharArr = sb.toString().toCharArray();
                for (char cc : reversedCharArr) {
                    aStack.push(cc);
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