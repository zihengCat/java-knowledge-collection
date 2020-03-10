package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println(
            new GenerateParentheses().generateParenthesis(3)
        );
    }
    private List<String> resultList = new LinkedList<>();
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new LinkedList<>();
        }
        //generateParenthesis0(0, 2 * n, "");
        generateParenthesis0(0, 0, n, "");
        return resultList;
    }
    private void generateParenthesis0(int left, int right, int max, String s) {
        if (left == max && right == max) {
            resultList.add(s);
            return;
        }
        if (left < max) {
            generateParenthesis0(left + 1, right, max, s + "(");
        }
        if (left > right) {
            generateParenthesis0(left, right + 1, max, s + ")");
        }
    }
    private void generateParenthesis0(int index, int length, String s) {
        // 终止条件
        if (index >= length) {
            if(isVaildParenthesis(s)) {
                resultList.add(s);
            }
            return;
        }

        // 处理逻辑
        // s1 = s +"("
        // s2 = s +")"

        // 递归下推
        generateParenthesis0(index + 1, length, s + "(");
        generateParenthesis0(index + 1, length, s + ")");
        // 状态恢复
    }
    private boolean isVaildParenthesis(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
/* EOF */