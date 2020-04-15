package io.ziheng.stack.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses obj = new ValidParentheses();
        String s = "{}";
        System.out.println(
            obj.isValid(s)
        );
    }
    public boolean isValid(String s) {
        // 暴力法
        // return isValidBruteForce(s);
        // 使用栈
        return isValidWithStack(s);
    }
    private boolean isValidWithStack(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Deque<Character> stack = new ArrayDeque<Character>();
        for (char c: s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if ((c == ')' && topChar != '(') ||
                    (c == ']' && topChar != '[') ||
                    (c == '}' && topChar != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    private boolean isValidBruteForce(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        while (s.indexOf("()") != -1
            || s.indexOf("[]") != -1
            || s.indexOf("{}") != -1) {
            if (s.indexOf("()") != -1) {
                s = s.replace("()", "");
            } else if (s.indexOf("[]") != -1) {
                s = s.replace("[]", "");
            } else if (s.indexOf("{}") != -1) {
                s = s.replace("{}", "");
            } else {
                // ...
            }
        }
        return s.length() == 0;
    }
}
/* EOF */