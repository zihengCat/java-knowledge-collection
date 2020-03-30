package io.ziheng.array.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 412. Fizz Buzz
 * https://leetcode.com/problems/fizz-buzz/
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> resultList = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                resultList.add("FizzBuzz");
            } else if (i % 3 == 0) {
                resultList.add("Fizz");
            } else if (i % 5 == 0) {
                resultList.add("Buzz");
            } else {
                resultList.add(String.valueOf(i));
            }
        }
        return resultList;
    }
}
/* EOF */