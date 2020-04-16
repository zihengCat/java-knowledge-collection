package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 1189. Maximum Number of Balloons
 * https://leetcode.com/problems/maximum-number-of-balloons/
 */
public class MaximumNumberOfBalloons {
    public static void main(String[] args) {
        MaximumNumberOfBalloons obj = new MaximumNumberOfBalloons();
        String text = "nlaebolko";
        System.out.println(
            obj.maxNumberOfBalloons(text)
        );
        text = "balon";
        System.out.println(
            obj.maxNumberOfBalloons(text)
        );
    }
    public int maxNumberOfBalloons(String text) {
        if (text == null || text.length() == 0) {
            return 0;
        }
        Map<Character, Integer> aMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            aMap.put(c, 1 + aMap.getOrDefault(c, 0));
        }
        int cnt = 0;
        String s = "balloon";
        while (subtractString(aMap, s)) {
            cnt++;
        }
        return cnt;
    }
    private boolean subtractString(Map<Character, Integer> aMap, String s) {
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (!aMap.containsKey(c)
                || aMap.get(c).intValue() <= 0) {
                return false;
            }
        }
        for (char c : arr) {
            aMap.put(c, aMap.get(c) - 1);
            if (aMap.get(c) < 0) {
                return false;
            }
        }
        return true;
    }
}
/* EOF */