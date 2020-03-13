import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(
            s.minRemove("mi)ho(y)o")
        );

        System.out.println(
            s.minRemove("m(i(h)o)yo)")
        );

        System.out.println(
            s.minRemove("))((")
        );

        System.out.println(
            s.minRemove("()")
        );

        System.out.println(
            s.minRemove("(")
        );

        System.out.println(
            s.minRemove(")")
        );

        System.out.println(
            s.minRemove("hello")
        );

        System.out.println(
            s.minRemove("hello))")
        );

        System.out.println(
            s.minRemove("(hello))")
        );

        System.out.println(
            s.minRemove("(h)(e))(llo))")
        );

        System.out.println(
            s.minRemove("(h)(e)(llo)")
        );

        System.out.println(
            s.minRemove("(((((hello))")
        );

    }

    /**
     * 移除括号
     * @param s string 字符串
     * @return string 字符串
     */
    public String minRemove (String s) {
        char[] arr = s.toCharArray();
        Deque<Character> primaryStack = new LinkedList<>();
        Deque<Character> secondStack = new LinkedList<>();
        for (char c : arr) {
            primaryStack.push(c);
            if (c == '(') {
                secondStack.push(c);
            } else if (c == ')') {
                if (secondStack.isEmpty()) {
                    primaryStack.pop();
                } else if (secondStack.peek() != '(') {
                    primaryStack.pop();
                    secondStack.pop();
                } else { // 合法
                    secondStack.pop();
                }
            }
        }
        // ...
        while (!secondStack.isEmpty()) {
            char c = secondStack.pop();
            System.out.println(c);
            primaryStack.pop();
        }
        StringBuilder sb = new StringBuilder();
        while (!primaryStack.isEmpty()) {
            sb.append(primaryStack.pop());
        }
        return sb.reverse().toString();
    }
}