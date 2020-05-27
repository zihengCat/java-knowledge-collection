package io.ziheng.codinginterviews;

import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 面试题 38：字符串的排列
 *
 * 题目描述：输入一枚字符串，输出该字符串的全排列。
 * 
 *
 * 知识点：["递归回溯"]
 */
public class StringPermutation {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        StringPermutation obj = new StringPermutation();
        String s = "abc";
        System.out.println(
            obj.stringPermutation(s)
        );
    }
    /**
     * 剑指 Offer 面试题 38：字符串的排列 -> 递归回溯
     *
     * 时间复杂度：O(n!)
     * 空间复杂度：O(n)
     *
     * @param s
     * @return {@code List<List<Character>>}
     */
    public List<List<Character>> stringPermutation(String s) {
        if (s == null || s.length() == 0) {
            return new LinkedList<>();
        }
        List<List<Character>> resultList = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        stringPermutation0(s.toCharArray(), 0, visited, new LinkedList<>(), resultList);
        return resultList;
    }
    private void stringPermutation0(
        char[] arr,
        int currentIndex,
        boolean[] visited,
        Deque<Character> aDeque,
        List<List<Character>> resultList) {
        if (currentIndex >= arr.length) {
            resultList.add(new LinkedList<>(aDeque));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                aDeque.push(arr[i]);
                stringPermutation0(arr, 1 + currentIndex, visited, aDeque, resultList);
                visited[i] = false;
                aDeque.pop();
            }
        }
    }
}
/* EOF */