package io.ziheng.stack.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 71. Simplify Path
 * https://leetcode.com/problems/simplify-path/
 */
public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath obj = new SimplifyPath();
        String path = "/home//foo/";
        path = "/a/./b/../../c/";
        path = "/a//b////c/d//././/..";
        System.out.println(
            obj.simplifyPath(path)
        );
    }
    public String simplifyPath(String path) {
        Deque<String> aStack = new LinkedList<>();
        String[] arr = path.split("/");
        for (String s : arr) {
            if (s.equals("")) {
                continue;
            } else if (s.equals(".")) {
                continue;
            } else if (s.equals("..")) {
                if (!aStack.isEmpty()) {
                    aStack.pop();
                }
            } else {
                aStack.push(s);
            }
        }
        // Reverse Stack
        // Use Deque features here...
        int size = aStack.size();
        for (int i = 0; i < size; i++) {
            aStack.offerLast(aStack.pollFirst());
        }
        // Construct UNIX absolute path -> /a/b/c
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        int index = 1;
        while (!aStack.isEmpty()) {
            sb.append(aStack.pop());
            if (index < size) {
                sb.append("/");
            }
            index++;
        }
        return sb.toString();
    }
}
/* EOF */